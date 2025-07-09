package sm.dsw.sgcp.request.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.math.BigDecimal;

/**
 *
 * @author jhochuq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionProductoRequest extends RequestBase {

    private Integer cotizacionId;
    private Integer productoId;
    private Integer cantidadSolicitada;
    private Integer cantidadCotizada;
    private BigDecimal precioUnitario;
}
