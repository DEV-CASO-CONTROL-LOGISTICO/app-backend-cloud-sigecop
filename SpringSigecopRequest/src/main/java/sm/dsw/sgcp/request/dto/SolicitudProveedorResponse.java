package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.http.response.ProveedorDTO;
import sm.dsw.sgcp.request.model.SolicitudProveedor;
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
public class SolicitudProveedorResponse extends DtoGeneric<SolicitudProveedor,SolicitudProveedorResponse> {
    private Integer id;
    private ProveedorDTO proveedor;
    private SolicitudResponse solicitud;

    @Override
    protected SolicitudProveedorResponse mapEntityToDto(SolicitudProveedor entity, SolicitudProveedorResponse dto) {
        dto.setId(entity.getId());
        //dto.setProveedor(ProveedorResponse.fromEntity(entity.getProveedor(), ProveedorResponse.class));
        dto.setSolicitud(SolicitudResponse.fromEntity(entity.getSolicitud(), SolicitudResponse.class));
        return dto;
    }

}
