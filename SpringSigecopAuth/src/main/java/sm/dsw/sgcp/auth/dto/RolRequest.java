package sm.dsw.sgcp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolRequest extends RequestBase {
    private String codigo;
    private String nombre;
    private List<Integer> paginas;

    public List<Integer> getPaginas(){
        if(paginas==null){
            paginas=new ArrayList<>();
        }
        return paginas;
    }

}
