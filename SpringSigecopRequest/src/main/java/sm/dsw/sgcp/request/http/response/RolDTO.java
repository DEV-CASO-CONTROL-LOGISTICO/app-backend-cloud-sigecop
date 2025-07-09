package sm.dsw.sgcp.request.http.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.Constants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Integer id;
    private String codigo;
    private String nombre;

    public Boolean getIsProveedor(){
        return codigo!=null && codigo.equals(Constants.ROL_PROVEEDOR);
    }

}
