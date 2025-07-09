package sm.dsw.sgcp.maintenance.dto;

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
public class ProductoRequest extends RequestBase {

    private Integer categoriaId;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;
}
