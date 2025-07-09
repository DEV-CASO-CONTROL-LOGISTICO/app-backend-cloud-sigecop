package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.model.EstadoSolicitud;
import sm.dsw.sgcp.util.clase.DtoGeneric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadoSolicitudResponse extends DtoGeneric<EstadoSolicitud,EstadoSolicitudResponse> {
    private Integer id;
    private String descripcion;
    private String detalle;

    @Override
    protected EstadoSolicitudResponse mapEntityToDto(EstadoSolicitud entity, EstadoSolicitudResponse dto) {
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDescripcion());
        dto.setDetalle(entity.getDetalle());
        return dto;
    }
}
