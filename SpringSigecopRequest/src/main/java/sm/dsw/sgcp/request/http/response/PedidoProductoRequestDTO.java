package sm.dsw.sgcp.request.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProductoRequestDTO extends RequestBase {

    private Integer pedidoId;
    private Integer productoId;
    private Integer cantidad;
    private BigDecimal monto;
}
