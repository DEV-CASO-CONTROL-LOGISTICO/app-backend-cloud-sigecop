package sm.dsw.sgcp.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.maintenance.model.Producto;
import sm.dsw.sgcp.util.clase.DtoGeneric;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse extends DtoGeneric<Producto,ProductoResponse> {

    private Integer id;
    private CategoriaResponse categoria;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;

    @Override
    protected ProductoResponse mapEntityToDto(Producto entity, ProductoResponse dto) {
        dto.setId(entity.getId());
        dto.setCategoria(CategoriaResponse.fromEntity(entity.getCategoria(),CategoriaResponse.class));
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        return dto;
    }

    public CategoriaResponse getCategoria() {
        if (categoria == null) {
            categoria = new CategoriaResponse();
        }
        return categoria;
    }
}
