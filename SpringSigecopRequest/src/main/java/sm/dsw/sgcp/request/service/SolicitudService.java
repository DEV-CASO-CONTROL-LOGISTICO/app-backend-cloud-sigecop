package sm.dsw.sgcp.request.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.request.client.ProductoClient;
import sm.dsw.sgcp.request.client.ProveedorClient;
import sm.dsw.sgcp.request.client.UsuarioClient;
import sm.dsw.sgcp.request.dto.*;
import sm.dsw.sgcp.request.http.response.ProductoDTO;
import sm.dsw.sgcp.request.http.response.ProveedorDTO;
import sm.dsw.sgcp.request.http.response.UsuarioDTO;
import sm.dsw.sgcp.request.model.*;
import sm.dsw.sgcp.request.repository.*;
import sm.dsw.sgcp.util.Constants;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;
import sm.dsw.sgcp.util.clase.RequestBase;

@Service
public class SolicitudService extends ServiceGeneric<SolicitudResponse, SolicitudRequest, Solicitud> {

    private final SolicitudRepository solicitudRepository;
    @Autowired
    private SolicitudProveedorRepository solicitudProveedorRepository;
    @Autowired
    private CotizacionRepository cotizacionRepository;
    @Autowired
    private SolicitudProductoRepository solicitudProductoRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private ProveedorClient proveedorClient;
    @Autowired
    private ProductoClient productoClient;
    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;

    public SolicitudService(SolicitudRepository _solicitudRepository) {
        super(SolicitudResponse.class, _solicitudRepository);
        this.solicitudRepository = _solicitudRepository;
    }

    @Override
    public List<Solicitud> listBase(SolicitudRequest filter) {
        return solicitudRepository.findByFilter(
                filter.getCodigo(),
                filter.getDescripcion(),
                filter.getEstadoId()
        );
    }

    @Override
    public ObjectResponse<SolicitudResponse> postFind(SolicitudResponse response) {
        if (response != null && response.getId() != null) {
            List<SolicitudProveedor> proveedores = solicitudProveedorRepository.findByFilters(response.getId());
            proveedores = proveedores != null ? proveedores : new ArrayList<>();
            for(SolicitudProveedor sp: proveedores){
                RequestBase rb=new RequestBase();
                rb.setId(sp.getProveedorId());
                ProveedorDTO pdto=proveedorClient.findById(rb);
                response.getProveedores().add(pdto);
            }

            List<SolicitudProducto> productos = solicitudProductoRepository.findByFilter(response.getId());
            productos = productos != null ? productos : new ArrayList<>();
            response.setSolicitudProducto(SolicitudProductoResponse.fromEntities(productos,SolicitudProductoResponse.class));
        }
        return new ObjectResponse<>(Boolean.TRUE, null, response);
    }

    public List<SolicitudResponse> listSolicitudByProveedor(SolicitudProveedorRequest filter) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) authentication.getPrincipal();

        RequestBase pUser=new RequestBase();
        pUser.setId(userId);
        UsuarioDTO user = usuarioClient.findById(pUser);
        //user.ifPresent(usuario -> filter.setProveedorId(usuario.getProveedor().getId()));

        List<SolicitudResponse> response = new ArrayList<SolicitudResponse>();
        if (filter != null && filter.getProveedorId() != null) {
            List<SolicitudProveedor> solicitudes = solicitudProveedorRepository.listSolicitudByProveedor(
                    filter.getProveedorId(),
                    filter.getEstadoId(),
                    filter.getCodigo(),
                    filter.getDescripcion()
            );
            solicitudes=solicitudes != null ? solicitudes : new ArrayList<>();
            for(SolicitudProveedor sp: solicitudes){
                SolicitudResponse sr=SolicitudResponse.fromEntity(sp.getSolicitud(),SolicitudResponse.class);
                sr.setSolicitudProveedorActualId(sp.getId());
                List<Cotizacion> cotizacion= cotizacionRepository.findByFilter(null,sp.getId(),null,null);
                if(cotizacion!=null && !cotizacion.isEmpty()){
                    CotizacionResponse cotizacionResponse=CotizacionResponse.fromEntity(cotizacion.get(0),CotizacionResponse.class);
                    if(cotizacion.get(0).getUsuarioCreacionId()!=null){
                        RequestBase rb=new RequestBase();
                        rb.setId(cotizacion.get(0).getUsuarioCreacionId());
                        cotizacionResponse.setUsuarioCreacion(usuarioClient.findById(rb));
                    }
                    if(cotizacion.get(0).getUsuarioEstadoId()!=null){
                        RequestBase rb=new RequestBase();
                        rb.setId(cotizacion.get(0).getUsuarioEstadoId());
                        cotizacionResponse.setUsuarioEstado(usuarioClient.findById(rb));
                    }
                    sr.setCotizacionActual(cotizacionResponse);
                }
                response.add(sr);
            }
        }
        return response;
    }

    @Override
    public ObjectResponse<Solicitud> recordToEntityEdit(Solicitud entity, SolicitudRequest request) {
        entity.setDescripcion(request.getDescripcion());
        //entity.setFechaCreacion(request.getFechaCreacion());
        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }

    @Override
    public ObjectResponse<Solicitud> recordToEntityNew(SolicitudRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) authentication.getPrincipal();

        RequestBase pUser=new RequestBase();
        pUser.setId(userId);
        UsuarioDTO optionalUsuario = usuarioClient.findById(pUser);
        if (optionalUsuario==null || optionalUsuario.getId()==null) {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el usuario de sesión",
                    null
            );
        }

        Solicitud entity = Solicitud.builder()
                .codigo(request.getCodigo())
                .descripcion(request.getDescripcion())
                .fechaCreacion(new Date())
                .usuarioCreacionId(userId)
                .usuarioEstadoId(userId)
                .build();

        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }

    @Override
    public ObjectResponse postSave(SolicitudRequest request, Solicitud entity) {
        List<SolicitudProveedor> solicitudProveedorInicial = solicitudProveedorRepository.findByFilters(entity.getId());
        solicitudProveedorInicial = solicitudProveedorInicial != null ? solicitudProveedorInicial : new ArrayList<>();
        for (SolicitudProveedor sp : solicitudProveedorInicial) {
            sp.setActivo(Boolean.FALSE);
            solicitudProveedorRepository.save(sp);
        }
        for (Integer proveedorId : request.getProveedores()) {
            RequestBase rb=new RequestBase();
            rb.setId(proveedorId);
            ProveedorDTO optionalProveedor = proveedorClient.findById(rb);
            if (optionalProveedor==null || optionalProveedor.getId()==null) {
                return new ObjectResponse(Boolean.TRUE, "Uno de los proveedores enviados no existe", null);
            }

            Optional<SolicitudProveedor> optionalResult = solicitudProveedorInicial.stream()
                    .filter(p -> p.getProveedorId().equals(proveedorId))
                    .findFirst();
            if (optionalResult.isPresent()) {
                SolicitudProveedor solicitudProveedorEdit = optionalResult.get();
                solicitudProveedorEdit.setActivo(Boolean.TRUE);
                solicitudProveedorRepository.save(solicitudProveedorEdit);
            } else {
                SolicitudProveedor solicitudProveedorNew = new SolicitudProveedor(null, optionalProveedor.getId(), entity);
                solicitudProveedorRepository.save(solicitudProveedorNew);
            }
        }

        List<SolicitudProducto> solicitudProductoInicial = solicitudProductoRepository.findByFilter(entity.getId());
        solicitudProductoInicial = solicitudProductoInicial != null ? solicitudProductoInicial : new ArrayList<>();
        for (SolicitudProducto sp : solicitudProductoInicial) {
            sp.setActivo(Boolean.FALSE);
            solicitudProductoRepository.save(sp);
        }
        for (SolicitudProductoRequest solicitudProductoRequest : request.getSolicitudProducto()) {
            RequestBase rb=new RequestBase();
            rb.setId(solicitudProductoRequest.getProductoId());
            ProductoDTO optionalProducto = productoClient.findById(rb);
            if (optionalProducto==null || optionalProducto.getId()==null) {
                return new ObjectResponse(Boolean.TRUE, "Uno de los productos enviados no existe", null);
            }

            Optional<SolicitudProducto> optionalResult = solicitudProductoInicial.stream()
                    .filter(p -> p.getId().equals(solicitudProductoRequest.getId()))
                    .findFirst();
            if (optionalResult.isPresent()) {
                SolicitudProducto solicitudProductoEdit = optionalResult.get();
                solicitudProductoEdit.setActivo(Boolean.TRUE);
                solicitudProductoEdit.setCantidad(solicitudProductoRequest.getCantidad());
                solicitudProductoEdit.setProductoId(solicitudProductoRequest.getProductoId());
                solicitudProductoRepository.save(solicitudProductoEdit);
            } else {
                SolicitudProducto solicitudProveedorNew = new SolicitudProducto(null, entity, solicitudProductoRequest.getProductoId(), solicitudProductoRequest.getCantidad());
                solicitudProductoRepository.save(solicitudProveedorNew);
            }
        }

        return new ObjectResponse(Boolean.TRUE, null, null);
    }

    public ObjectResponse finalizar(SolicitudRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) authentication.getPrincipal();

        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(request.getId());
        if (optionalSolicitud.isEmpty()) {
            return new ObjectResponse<>(Boolean.FALSE, "No se encontró la solicitud", null);
        }

        RequestBase pUser=new RequestBase();
        pUser.setId(userId);
        UsuarioDTO optionalUsuario = usuarioClient.findById(pUser);
        if (optionalUsuario==null || optionalUsuario.getId()==null) {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el usuario de sesión",
                    null
            );
        }

        EstadoSolicitud estado;
        Optional<EstadoSolicitud> optionalEstado = estadoSolicitudRepository.findById(Constants.EstadoSolicitud.FINALIZADO);
        if (optionalEstado.isPresent()) {
            estado = optionalEstado.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el estado",
                    null
            );
        }

        Solicitud solicitud = optionalSolicitud.get();
        solicitud.setEstado(estado);
        solicitud.setFechaFinalizado(new Date());
        solicitud.setUsuarioEstadoId(userId);
        solicitudRepository.save(solicitud);
        return new ObjectResponse<>(Boolean.TRUE, null, null);
    }

    @Override
    public SolicitudResponse convertReturnObject(Solicitud record){
        SolicitudResponse rec=SolicitudResponse.fromEntity(record,SolicitudResponse.class);
        if(record.getUsuarioCreacionId()!=null){
            RequestBase rb=new RequestBase();
            rb.setId(record.getUsuarioCreacionId());
            rec.setUsuarioCreacion(usuarioClient.findById(rb));
        }
        if(record.getUsuarioEstadoId()!=null){
            RequestBase rb=new RequestBase();
            rb.setId(record.getUsuarioEstadoId());
            rec.setUsuarioEstado(usuarioClient.findById(rb));
        }
        return rec;
    }

    @Override
    public List<SolicitudResponse> convertReturnList(List<Solicitud> result){
        List<SolicitudResponse> list= new ArrayList<>();
        if(result!=null){
            for(Solicitud temp: result){
                list.add(convertReturnObject(temp));
            }
        }
        return list;
    }

}

