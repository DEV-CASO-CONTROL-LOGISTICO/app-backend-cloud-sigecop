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
public class EstadoSolicitudRequest extends RequestBase {

    private String descripcion;
    private String detalle;

}
