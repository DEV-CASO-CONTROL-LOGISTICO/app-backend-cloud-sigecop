package sm.dsw.sgcp.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.auth.client.ProveedorClient;
import sm.dsw.sgcp.auth.dto.PaginaResponse;
import sm.dsw.sgcp.auth.dto.ProveedorDTO;
import sm.dsw.sgcp.auth.dto.UsuarioRequest;
import sm.dsw.sgcp.auth.dto.UsuarioResponse;
import sm.dsw.sgcp.auth.model.Rol;
import sm.dsw.sgcp.auth.model.Usuario;
import sm.dsw.sgcp.auth.repository.PaginaRepository;
import sm.dsw.sgcp.auth.repository.RolRepository;
import sm.dsw.sgcp.auth.repository.UsuarioRepository;
import sm.dsw.sgcp.auth.util.Encrypt;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;
import sm.dsw.sgcp.util.clase.RequestBase;

/**
 *
 * @author Moises_F16.7.24
 */
@Service
public class UsuarioService extends ServiceGeneric<UsuarioResponse, UsuarioRequest, Usuario> {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    PaginaRepository paginaRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    ProveedorClient proveedorClient;

    public UsuarioService(UsuarioRepository _usuarioRepository) {
        super(UsuarioResponse.class,_usuarioRepository);
        this.usuarioRepository = _usuarioRepository;
    }

    public UsuarioResponse searchForCredentials(UsuarioRequest request) {
        UsuarioResponse usuarioResponse=UsuarioResponse.fromEntity(
                usuarioRepository.getUserForCredentials(
                        request.getCuenta(),
                        Encrypt.hashClave(request.getClave())
                ),UsuarioResponse.class);
        if(usuarioResponse!=null && usuarioResponse.getId()!=null){
            usuarioResponse.setPaginas(PaginaResponse.fromEntities(
                    paginaRepository.listForRol(usuarioResponse.getRol().getId()),PaginaResponse.class));
        }
        return usuarioResponse;
    }

    public UsuarioResponse searchInfoForId(Integer usuarioId) {
        Optional<Usuario> result = usuarioRepository.findById(usuarioId);
        if (!result.isPresent()) {
            return null;
        }
        UsuarioResponse usuarioResponse = UsuarioResponse.fromEntity(result.get(),UsuarioResponse.class);
        usuarioResponse.setPaginas(PaginaResponse.fromEntities(
                paginaRepository.listForRol(usuarioResponse.getRol().getId()),PaginaResponse.class));
        return usuarioResponse;
    }

    @Override
    public List<Usuario> listBase(UsuarioRequest filter) {
        return usuarioRepository.findByFilter(
                filter.getNombre(),
                filter.getApellidoPaterno(),
                filter.getApellidoMaterno(),
                filter.getRolId()
        );
    }

    @Override
    public ObjectResponse<Usuario> recordToEntityEdit(Usuario entity, UsuarioRequest request) {
        Rol rol;
        ProveedorDTO proveedorDTO=null;
        Optional<Rol> rolOptional = rolRepository.findById(request.getRolId());
        if (rolOptional.isPresent()) {
            rol=rolOptional.get();
        }else{
            return new ObjectResponse(
                    Boolean.FALSE,
                    "No se encontró el rol ingresado",
                    null);
        }
        if(request.getProveedorId()!=null ){
            RequestBase rb=new RequestBase();
            rb.setId(request.getProveedorId());
            proveedorDTO=proveedorClient.findById(rb);
            if (proveedorDTO==null || proveedorDTO.getId()==null) {
                return new ObjectResponse(
                        Boolean.FALSE,
                        "No se encontró el proveedor ingresado",
                        null);
            }
        }
        entity.setRol(rol);
        entity.setProveedorId(request.getProveedorId());
        entity.setNombre(request.getNombre());
        entity.setApellidoPaterno(request.getApellidoPaterno());
        entity.setApellidoMaterno(request.getApellidoMaterno());
        if (request.getUpdatePassword() != null && request.getUpdatePassword()) {
            entity.setClave(Encrypt.hashClave(request.getClave()));
        }
        return new ObjectResponse<>(Boolean.TRUE,null,entity);
    }

    @Override
    public ObjectResponse<Usuario> recordToEntityNew(UsuarioRequest request) {
        Rol rol;
        ProveedorDTO proveedorDTO=null;
        Optional<Rol> rolOptional = rolRepository.findById(request.getRolId());
        if (rolOptional.isPresent()) {
            rol=rolOptional.get();
        }else{
            return new ObjectResponse(
                    Boolean.FALSE,
                    "No se encontró el rol ingresado",
                    null);
        }
        if(request.getProveedorId()!=null ){
            RequestBase rb=new RequestBase();
            rb.setId(request.getProveedorId());
            proveedorDTO=proveedorClient.findById(rb);
            if (proveedorDTO==null || proveedorDTO.getId()==null) {
                return new ObjectResponse(
                        Boolean.FALSE,
                        "No se encontró el proveedor ingresado",
                        null);
            }
        }
        return new ObjectResponse<>(Boolean.TRUE,null,new Usuario(
                request.getId(),
                rol,
                request.getProveedorId(),
                request.getNombre(),
                request.getApellidoPaterno(),
                request.getApellidoMaterno(),
                request.getCuenta(),
                Encrypt.hashClave(request.getClave())
        ));
    }

    @Override
    public UsuarioResponse convertReturnObject(Usuario record){
        RequestBase rb=new RequestBase();
        rb.setId(record.getProveedorId());
        UsuarioResponse rec=UsuarioResponse.fromEntity(record,UsuarioResponse.class);
        rec.setProveedor(proveedorClient.findById(rb));
        return rec;
    }

    @Override
    public List<UsuarioResponse> convertReturnList(List<Usuario> result){
        List<UsuarioResponse> list= new ArrayList<>();
        if(result!=null){
            for(Usuario temp: result){
                RequestBase rb=new RequestBase();
                rb.setId(temp.getProveedorId());
                UsuarioResponse rec=UsuarioResponse.fromEntity(temp,UsuarioResponse.class);
                rec.setProveedor(proveedorClient.findById(rb));
                list.add(rec);
            }
        }
        return list;
    }

}
