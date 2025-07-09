package sm.dsw.sgcp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.auth.model.Rol;
import sm.dsw.sgcp.auth.util.Constants;
import sm.dsw.sgcp.util.clase.DtoGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolResponse extends DtoGeneric<Rol,RolResponse> {

    private Integer id;
    private String codigo;
    private String nombre;
    private List<Integer> paginas;

    public List<Integer> getPaginas(){
        if(paginas==null){
            paginas=new ArrayList<>();
        }
        return paginas;
    }

    @Override
    protected RolResponse mapEntityToDto(Rol entity, RolResponse dto) {
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public Boolean getIsProveedor(){
        return codigo!=null && codigo.equals(Constants.ROL_PROVEEDOR);
    }

}
