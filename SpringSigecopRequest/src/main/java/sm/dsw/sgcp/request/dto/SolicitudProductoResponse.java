package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.http.response.ProductoDTO;
import sm.dsw.sgcp.request.model.SolicitudProducto;
import sm.dsw.sgcp.util.clase.DtoGeneric;
/**
 *
 * @author Diego Poma
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudProductoResponse extends DtoGeneric<SolicitudProducto,SolicitudProductoResponse> {

    private Integer id;
    private Integer cantidad;
    private SolicitudResponse solicitud;
    private ProductoDTO producto;

    @Override
    protected SolicitudProductoResponse mapEntityToDto(SolicitudProducto entity, SolicitudProductoResponse dto) {
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());
        dto.setSolicitud(SolicitudResponse.fromEntity(entity.getSolicitud(),SolicitudResponse.class));
        //dto.setProducto(ProductoResponse.fromEntity(entity.getProducto(),ProductoResponse.class));
        return dto;
    }
}
