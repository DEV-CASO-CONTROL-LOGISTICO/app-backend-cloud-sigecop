package sm.dsw.sgcp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.auth.model.Pagina;
import sm.dsw.sgcp.util.clase.DtoGeneric;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaResponse extends DtoGeneric<Pagina, PaginaResponse> {

    private Integer id;
    private String nombre;
    private String url;

    @Override
    protected PaginaResponse mapEntityToDto(Pagina entity, PaginaResponse dto) {
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setUrl(entity.getUrl());
        return dto;
    }

}
