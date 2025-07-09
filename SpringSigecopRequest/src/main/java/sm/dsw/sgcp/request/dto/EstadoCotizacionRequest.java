package sm.dsw.sgcp.request.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 * @author jhochuq
 */
public class EstadoCotizacionRequest extends RequestBase {

    private String descripcion;
    private String detalle;
}
