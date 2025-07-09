package sm.dsw.sgcp.request.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.request.http.response.UsuarioDTO;
import sm.dsw.sgcp.request.model.Cotizacion;
import sm.dsw.sgcp.util.Constants;
import sm.dsw.sgcp.util.clase.DtoGeneric;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CotizacionResponse extends DtoGeneric<Cotizacion, CotizacionResponse> {

    private Integer id;
    private SolicitudProveedorResponse solicitudProveedor;
    private EstadoCotizacionResponse estado;
    private String codigo;
    private BigDecimal monto;
    private Date fechaEmision;
    private String comentario;
    private UsuarioDTO usuarioCreacion;
    private UsuarioDTO usuarioEstado;
    private List<CotizacionProductoResponse> cotizacionProducto;

    @Override
    protected CotizacionResponse mapEntityToDto(Cotizacion entity, CotizacionResponse dto) {
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setMonto(entity.getMonto());
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setComentario(entity.getComentario());
        dto.setSolicitudProveedor(SolicitudProveedorResponse.fromEntity(entity.getSolicitudProveedor(), SolicitudProveedorResponse.class));
        //dto.setUsuarioEstado(UsuarioResponse.fromEntity(entity.getUsuarioEstado(), UsuarioResponse.class));
        //dto.setUsuarioCreacion(UsuarioResponse.fromEntity(entity.getUsuarioCreacion(), UsuarioResponse.class));
        dto.setEstado(EstadoCotizacionResponse.fromEntity(entity.getEstado(), EstadoCotizacionResponse.class));
        return dto;
    }

    public List<CotizacionProductoResponse> getCotizacionProducto() {
        if (cotizacionProducto == null) {
            cotizacionProducto = new ArrayList<>();
        }
        return cotizacionProducto;
    }

    public Boolean getFinalizado() {
        return estado != null && estado.getId() != null
                && (estado.getId().equals(Constants.EstadoCotizacion.ARCHIVADO) || estado.getId().equals(Constants.EstadoCotizacion.APROBADO));
    }
}
