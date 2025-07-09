package sm.dsw.sgcp.request.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO extends RequestBase {

    private String codigo;
    private Integer proveedorId;
    private String proveedorRazonSocial;
    private Integer estadoId;
    private String descripcion;
    private String observacion;
    private BigDecimal montoTotal;
    private Integer usuarioCreacionId;
    private Integer usuarioEstadoId;
    private Date fechaRegistro;
    private String numeroFactura;
    private String serieGuia;
    private String numeroGuia;
    private Date fechaEntrega;
    private String observacionEnvio;
    private List<PedidoProductoRequestDTO> pedidoProducto;

    public List<PedidoProductoRequestDTO> getPedidoProducto() {
        if (pedidoProducto == null) {
            pedidoProducto = new ArrayList<>();
        }
        return pedidoProducto;
    }
}
