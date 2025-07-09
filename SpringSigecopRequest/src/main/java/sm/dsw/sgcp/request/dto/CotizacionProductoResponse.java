package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.http.response.ProductoDTO;
import sm.dsw.sgcp.request.model.CotizacionProducto;
import sm.dsw.sgcp.util.clase.DtoGeneric;

import java.math.BigDecimal;

/**
 *
 * @author jhochuq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CotizacionProductoResponse extends DtoGeneric<CotizacionProducto, CotizacionProductoResponse> {

    private Integer id;
    private Integer cantidadSolicitado;
    private Integer cantidadCotizada;
    private BigDecimal precioUnitario;
    private CotizacionResponse cotizacion;
    private ProductoDTO producto;

    @Override
    protected CotizacionProductoResponse mapEntityToDto(CotizacionProducto entity, CotizacionProductoResponse dto) {
        dto.setId(entity.getId());
        dto.setCantidadSolicitado(entity.getCantidadSolicitada());
        dto.setCantidadCotizada(entity.getCantidadCotizada());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setCotizacion(CotizacionResponse.fromEntity(entity.getCotizacion(), CotizacionResponse.class));
        //dto.setProducto(ProductoResponse.fromEntity(entity.getProducto(), ProductoResponse.class));
        return dto;
    }
}
