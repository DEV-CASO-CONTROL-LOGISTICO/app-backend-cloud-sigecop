package sm.dsw.sgcp.auth.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.auth.model.Usuario;
import sm.dsw.sgcp.util.clase.DtoGeneric;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse extends DtoGeneric<Usuario,UsuarioResponse> {

    private Integer id;
    private RolResponse rol;
    private ProveedorDTO proveedor;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String cuenta;
    private List<PaginaResponse> paginas;

    @Override
    protected UsuarioResponse mapEntityToDto(Usuario entity, UsuarioResponse dto) {
        dto.setId(entity.getId());
        dto.setRol(fromEntity(entity.getRol(),RolResponse.class));
        //dto.setProveedor(ProveedorResponse.fromEntity(entity.getProveedor(),ProveedorResponse.class));
        dto.setNombre(entity.getNombre());
        dto.setApellidoPaterno(entity.getApellidoPaterno());
        dto.setApellidoMaterno(entity.getApellidoMaterno());
        dto.setCuenta(entity.getCuenta());
        return dto;
    }

    public RolResponse getRol() {
        if (rol == null) {
            rol = new RolResponse();
        }
        return rol;
    }

    public ProveedorDTO getProveedor() {
        if (proveedor == null) {
            proveedor = new ProveedorDTO();
        }
        return proveedor;
    }

    public List<PaginaResponse> getPaginas() {
        if (paginas == null) {
            paginas = new ArrayList<>();
        }
        return paginas;
    }

}
