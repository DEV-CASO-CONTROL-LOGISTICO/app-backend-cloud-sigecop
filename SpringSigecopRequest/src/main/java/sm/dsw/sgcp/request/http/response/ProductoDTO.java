package sm.dsw.sgcp.request.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer id;
    private CategorioDTO categoria;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;

    public CategorioDTO getCategoria() {
        if (categoria == null) {
            categoria = new CategorioDTO();
        }
        return categoria;
    }
}
