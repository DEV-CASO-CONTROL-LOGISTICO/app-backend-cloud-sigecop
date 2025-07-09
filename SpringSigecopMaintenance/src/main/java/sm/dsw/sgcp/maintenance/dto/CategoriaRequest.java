package sm.dsw.sgcp.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.RequestBase;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest extends RequestBase {

    private String nombre;
}
