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
import sm.dsw.sgcp.request.client.PedidoClient;
import sm.dsw.sgcp.request.client.ProductoClient;
import sm.dsw.sgcp.request.client.UsuarioClient;
import sm.dsw.sgcp.request.dto.*;
import sm.dsw.sgcp.request.http.response.PedidoProductoRequestDTO;
import sm.dsw.sgcp.request.http.response.PedidoRequestDTO;
import sm.dsw.sgcp.request.http.response.ProductoDTO;
import sm.dsw.sgcp.request.http.response.UsuarioDTO;
import sm.dsw.sgcp.request.model.*;
import sm.dsw.sgcp.request.repository.*;
import sm.dsw.sgcp.util.Constants;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;
import sm.dsw.sgcp.util.clase.RequestBase;

/**
 *
 * @author AlexChuky
 */
@Service
public class CotizacionService extends ServiceGeneric<CotizacionResponse, CotizacionRequest, Cotizacion> {

    private final CotizacionRepository cotizacionRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private EstadoCotizacionRepository estadoCotizacionRepository;
    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;
    @Autowired
    private PedidoClient pedidoClient;
    @Autowired
    private ProductoClient productoClient;
    @Autowired
    private CotizacionProductoRepository cotizacionProductoRepository;
    @Autowired
    private SolicitudProveedorRepository solicitudProveedorRepository;

    public CotizacionService(CotizacionRepository _cotizacionRepository) {
        super(CotizacionResponse.class, _cotizacionRepository);
        this.cotizacionRepository = _cotizacionRepository;
    }

    @Override
    public List<Cotizacion> listBase(CotizacionRequest filter) {
        return cotizacionRepository.findByFilter(
                filter.getSolicitudId(),
                filter.getSolicitudProveedorId(),
                filter.getCodigo(),
                filter.getEstadoId()
        );
    }

    @Override
    public ObjectResponse<CotizacionResponse> postFind(CotizacionResponse response) {
        if (response != null && response.getId() != null) {
            List<CotizacionProducto> cotizacionProducto = cotizacionProductoRepository.findByFilter(response.getId());
            cotizacionProducto = cotizacionProducto != null ? cotizacionProducto : new ArrayList<>();
            response.setCotizacionProducto(cotizacionProducto.stream()
                    .map(cp -> CotizacionProductoResponse.fromEntity(cp, CotizacionProductoResponse.class))
                    .collect(Collectors.toList()));
        }
        return new ObjectResponse<>(Boolean.TRUE, null, response);
    }

    @Override
    public ObjectResponse<Cotizacion> recordToEntityEdit(Cotizacion entity, CotizacionRequest request) {
        entity.setMonto(request.getMonto());
        entity.setComentario(request.getComentario());
        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }

    @Override
    public ObjectResponse<Cotizacion> recordToEntityNew(CotizacionRequest request) {
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
        SolicitudProveedor solicitudProveedor;
        Optional<SolicitudProveedor> optionalSolicitudProveedor = solicitudProveedorRepository.findById(request.getSolicitudProveedorId());
        if (optionalSolicitudProveedor.isPresent()) {
            solicitudProveedor = optionalSolicitudProveedor.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró la solicitud del proveedor",
                    null
            );
        }

        EstadoCotizacion estado;
        Optional<EstadoCotizacion> optionalEstado = estadoCotizacionRepository.findById(Constants.EstadoCotizacion.GENERADO);
        if (optionalEstado.isPresent()) {
            estado = optionalEstado.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el estado",
                    null
            );
        }

        Cotizacion entity = Cotizacion.builder()
                .solicitudProveedor(solicitudProveedor)
                .estado(estado) //Nace con un estado Generado
                .monto(request.getMonto())
                .comentario(request.getComentario())
                .fechaEmision(new Date())
                .usuarioCreacionId(userId)
                .usuarioEstadoId(userId)
                .build();

        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }

    @Override
    public ObjectResponse postSave(CotizacionRequest request, Cotizacion entity) {
        List<CotizacionProducto> cotizacionProductoInicial = cotizacionProductoRepository.findByFilter(entity.getId());
        cotizacionProductoInicial = cotizacionProductoInicial != null ? cotizacionProductoInicial : new ArrayList<>();
        for (CotizacionProducto sp : cotizacionProductoInicial) {
            sp.setActivo(Boolean.FALSE);
            cotizacionProductoRepository.save(sp);
        }
        for (CotizacionProductoRequest cotizacionProductoRequest : request.getCotizacionProducto()) {
           RequestBase rb=new RequestBase();
            rb.setId(cotizacionProductoRequest.getProductoId());
            ProductoDTO optionalProducto = productoClient.findById(rb);
            if (optionalProducto==null && optionalProducto.getId()==null) {
                return new ObjectResponse(Boolean.TRUE, "Uno de los productos cotizados no existe", null);
            }

            Optional<CotizacionProducto> optionalResult = cotizacionProductoInicial.stream()
                    .filter(p -> p.getId().equals(cotizacionProductoRequest.getId()))
                    .findFirst();
            if (optionalResult.isPresent()) {
                CotizacionProducto cotizacionProductoEdit = optionalResult.get();
                cotizacionProductoEdit.setActivo(Boolean.TRUE);
                cotizacionProductoEdit.setCantidadSolicitada(cotizacionProductoRequest.getCantidadSolicitada());
                cotizacionProductoEdit.setCantidadCotizada(cotizacionProductoRequest.getCantidadCotizada());
                cotizacionProductoEdit.setPrecioUnitario(cotizacionProductoRequest.getPrecioUnitario());
                cotizacionProductoEdit.setProductoId(cotizacionProductoRequest.getProductoId());
                cotizacionProductoRepository.save(cotizacionProductoEdit);
            } else {
                CotizacionProducto cotizacionProductoNew = new CotizacionProducto(null, entity, cotizacionProductoRequest.getProductoId(), cotizacionProductoRequest.getCantidadSolicitada(),cotizacionProductoRequest.getCantidadCotizada(),cotizacionProductoRequest.getPrecioUnitario());
                cotizacionProductoRepository.save(cotizacionProductoNew);
            }
        }

        return new ObjectResponse(Boolean.TRUE, null, null);
    }


    public ObjectResponse aprobar(CotizacionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) authentication.getPrincipal();

        Optional<Cotizacion> optionalCotizacion = cotizacionRepository.findById(request.getId());
        if (optionalCotizacion.isEmpty()) {
            return new ObjectResponse<>(Boolean.FALSE, "No se encontró la cotización", null);
        }

        Optional<Solicitud> optionalSolicitud = solicitudRepository.findById(optionalCotizacion.get().getSolicitudProveedor().getSolicitud().getId());
        if (optionalSolicitud.isEmpty()) {
            return new ObjectResponse<>(Boolean.FALSE, "No se encontró la solicitud de la cotización", null);
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

        EstadoCotizacion estadoCotizacion;
        Optional<EstadoCotizacion> optionalEstadoCotizacion = estadoCotizacionRepository.findById(Constants.EstadoCotizacion.APROBADO);
        if (optionalEstadoCotizacion.isPresent()) {
            estadoCotizacion = optionalEstadoCotizacion.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el estado de cotización asignado",
                    null
            );
        }
        EstadoSolicitud estadoSolicitud;
        Optional<EstadoSolicitud> optionalEstadoSolicitud = estadoSolicitudRepository.findById(Constants.EstadoSolicitud.FINALIZADO);
        if (optionalEstadoSolicitud.isPresent()) {
            estadoSolicitud = optionalEstadoSolicitud.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el estado de solicitud asignado",
                    null
            );
        }

        //ACTUALIZA COTIZACION
        Cotizacion cotizacion = optionalCotizacion.get();
        cotizacion.setEstado(estadoCotizacion);
        cotizacion.setUsuarioEstadoId(userId);
        cotizacionRepository.save(cotizacion);

        //ACTUALIZA SOLICITUD
        Solicitud solicitud = optionalSolicitud.get();
        solicitud.setEstado(estadoSolicitud);
        solicitud.setFechaFinalizado(new Date());
        solicitud.setUsuarioEstadoId(userId);
        solicitudRepository.save(solicitud);

        //CREA UN PEDIDO
        PedidoRequestDTO pedido=new PedidoRequestDTO();
        pedido.setProveedorId(cotizacion.getSolicitudProveedor().getProveedorId());
        pedido.setDescripcion(solicitud.getDescripcion());
        pedido.setObservacion(cotizacion.getComentario());
        pedido.setMontoTotal(cotizacion.getMonto());
        pedido.setUsuarioCreacionId(userId);
        pedido.setUsuarioEstadoId(userId);
        pedido.setFechaRegistro(new Date());
        //pedido=pedidoRepository.save(pedido);

        List<CotizacionProducto> cp= cotizacionProductoRepository.findByFilter(cotizacion.getId());
        for (CotizacionProducto cpTemp: cp){
            PedidoProductoRequestDTO pp=new PedidoProductoRequestDTO();
            pp.setProductoId(cpTemp.getProductoId());
            pp.setCantidad(cpTemp.getCantidadCotizada());
            pp.setMonto(cpTemp.getPrecioUnitario());
            pedido.getPedidoProducto().add(pp);
            //pedidoProductoRepository.save(pp);
        }
        pedidoClient.save(pedido);

        return new ObjectResponse<>(Boolean.TRUE, null, null);
    }

    public ObjectResponse archivar(CotizacionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) authentication.getPrincipal();

        Optional<Cotizacion> optionalCotizacion = cotizacionRepository.findById(request.getId());
        if (optionalCotizacion.isEmpty()) {
            return new ObjectResponse<>(Boolean.FALSE, "No se encontró la cotización", null);
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

        EstadoCotizacion estado;
        Optional<EstadoCotizacion> optionalEstado = estadoCotizacionRepository.findById(Constants.EstadoCotizacion.ARCHIVADO);
        if (optionalEstado.isPresent()) {
            estado = optionalEstado.get();
        } else {
            return new ObjectResponse<>(
                    Boolean.FALSE,
                    "No se encontró el estado asignado",
                    null
            );
        }

        Cotizacion cotizacion = optionalCotizacion.get();
        cotizacion.setEstado(estado);
        cotizacion.setUsuarioEstadoId(userId);
        cotizacionRepository.save(cotizacion);
        return new ObjectResponse<>(Boolean.TRUE, null, null);
    }

    @Override
    public CotizacionResponse convertReturnObject(Cotizacion record){
        CotizacionResponse rec=CotizacionResponse.fromEntity(record,CotizacionResponse.class);
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
    public List<CotizacionResponse> convertReturnList(List<Cotizacion> result){
        List<CotizacionResponse> list= new ArrayList<>();
        if(result!=null){
            for(Cotizacion temp: result){
                list.add(convertReturnObject(temp));
            }
        }
        return list;
    }

}
