package sm.dsw.sgcp.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.model.EstadoCotizacion;
import sm.dsw.sgcp.util.clase.DtoGeneric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadoCotizacionResponse extends DtoGeneric<EstadoCotizacion,EstadoCotizacionResponse> {

    private Integer id;
    private String descripcion;
    private String detalle;

    @Override
    protected EstadoCotizacionResponse mapEntityToDto(EstadoCotizacion entity, EstadoCotizacionResponse dto) {
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDescripcion());
        dto.setDetalle(entity.getDetalle());
        return dto;
    }
}