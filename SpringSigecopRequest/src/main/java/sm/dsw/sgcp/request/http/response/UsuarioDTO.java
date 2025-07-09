package sm.dsw.sgcp.request.http.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private RolDTO rol;
    private ProveedorDTO proveedor;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String cuenta;

    public RolDTO getRol() {
        if (rol == null) {
            rol = new RolDTO();
        }
        return rol;
    }

    public ProveedorDTO getProveedor() {
        if (proveedor == null) {
            proveedor = new ProveedorDTO();
        }
        return proveedor;
    }

}
