package sm.dsw.sgcp.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;
/**
 *
 * @author Diego Poma
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudProveedorRequest extends RequestBase{

    private Integer solicitudId;
    private Integer proveedorId;
    private Integer estadoId;
    private String codigo;
    private String descripcion;
}
