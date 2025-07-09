package sm.dsw.sgcp.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequest extends RequestBase {

    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private String correo;

}
