package sm.dsw.sgcp.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.maintenance.model.Categoria;
import sm.dsw.sgcp.util.clase.DtoGeneric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse extends DtoGeneric<Categoria,CategoriaResponse> {
    private Integer id;
    private String nombre;

    @Override
    protected CategoriaResponse mapEntityToDto(Categoria entity, CategoriaResponse dto) {
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}
