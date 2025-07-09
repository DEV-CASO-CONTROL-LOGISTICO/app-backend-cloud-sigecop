package sm.dsw.sgcp.util.clase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Moises_F16.7.24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectResponse<T> {

    private Boolean success;
    private String message;
    private T object;
}
