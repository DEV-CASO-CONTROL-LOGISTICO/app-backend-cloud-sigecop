package sm.dsw.sgcp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.auth.model.Permiso;
import sm.dsw.sgcp.util.clase.DtoGeneric;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermisoResponse extends DtoGeneric<Permiso, PermisoResponse> {

    private Integer id;
    private RolResponse rol;
    private PaginaResponse pagina;

    @Override
    protected PermisoResponse mapEntityToDto(Permiso entity, PermisoResponse dto) {
        dto.setId(entity.getId());
        dto.setRol(RolResponse.fromEntity(entity.getRol(), RolResponse.class));
        dto.setPagina(PaginaResponse.fromEntity(entity.getPagina(), PaginaResponse.class));
        return dto;
    }

}
