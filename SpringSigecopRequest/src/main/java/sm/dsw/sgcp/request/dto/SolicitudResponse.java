package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.http.response.ProveedorDTO;
import sm.dsw.sgcp.request.http.response.UsuarioDTO;
import sm.dsw.sgcp.request.model.Solicitud;
import sm.dsw.sgcp.util.Constants;
import sm.dsw.sgcp.util.clase.DtoGeneric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudResponse extends DtoGeneric<Solicitud, SolicitudResponse> {

    private Integer id;
    private String codigo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaFinalizado;
    private UsuarioDTO usuarioCreacion;
    private UsuarioDTO usuarioEstado;
    private EstadoSolicitudResponse estado;
    private List<ProveedorDTO> proveedores;
    private List<SolicitudProductoResponse> solicitudProducto;

    private Integer solicitudProveedorActualId;
    private CotizacionResponse cotizacionActual;

    @Override
    protected SolicitudResponse mapEntityToDto(Solicitud entity, SolicitudResponse dto) {
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setFechaFinalizado(entity.getFechaFinalizado());
        //dto.setUsuarioCreacion(UsuarioResponse.fromEntity(entity.getUsuarioCreacion(), UsuarioResponse.class));
        //dto.setUsuarioEstado(UsuarioResponse.fromEntity(entity.getUsuarioEstado(), UsuarioResponse.class));
        dto.setEstado(EstadoSolicitudResponse.fromEntity(entity.getEstado(), EstadoSolicitudResponse.class));
        return dto;
    }

    public List<ProveedorDTO> getProveedores() {
        if (proveedores == null) {
            proveedores = new ArrayList<>();
        }
        return proveedores;
    }

    public List<SolicitudProductoResponse> getSolicitudProducto() {
        if (solicitudProducto == null) {
            solicitudProducto = new ArrayList<>();
        }
        return solicitudProducto;
    }

    public CotizacionResponse getCotizacionActual() {
        if (cotizacionActual == null) {
            cotizacionActual = new CotizacionResponse();
        }
        return cotizacionActual;
    }

    public Boolean getFinalizado() {
        return estado != null && estado.getId() != null && estado.getId().equals(Constants.EstadoSolicitud.FINALIZADO);
    }
}
