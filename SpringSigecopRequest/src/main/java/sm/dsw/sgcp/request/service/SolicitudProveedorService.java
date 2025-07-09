package sm.dsw.sgcp.request.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.request.dto.SolicitudProveedorRequest;
import sm.dsw.sgcp.request.dto.SolicitudProveedorResponse;
import sm.dsw.sgcp.request.model.SolicitudProveedor;
import sm.dsw.sgcp.request.repository.EstadoSolicitudRepository;
import sm.dsw.sgcp.request.repository.SolicitudProductoRepository;
import sm.dsw.sgcp.request.repository.SolicitudProveedorRepository;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;

/**
 *
 * @author alexChuky
 */
public class SolicitudProveedorService extends ServiceGeneric<SolicitudProveedorResponse, SolicitudProveedorRequest, SolicitudProveedor> {
    private final SolicitudProveedorRepository solicitudProveedorRepository;
    @Autowired
    private SolicitudProductoRepository solicitudProductoRepository;
    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;

    public SolicitudProveedorService(SolicitudProveedorRepository _solicitudProveedorRepository) {
        super(SolicitudProveedorResponse.class, _solicitudProveedorRepository);
        this.solicitudProveedorRepository = _solicitudProveedorRepository;
    }

    @Override
    public List<SolicitudProveedor> listBase(SolicitudProveedorRequest filter) {
        return solicitudProveedorRepository.findByFilters(
                filter.getSolicitudId()
        );
    }

    @Override
    public ObjectResponse<SolicitudProveedorResponse> postFind(SolicitudProveedorResponse response) {
        return new ObjectResponse<>(Boolean.TRUE, null, response);
    }

    @Override
    public ObjectResponse<SolicitudProveedor> recordToEntityNew(SolicitudProveedorRequest request) {

        SolicitudProveedor entity = SolicitudProveedor.builder()
                .proveedorId(null)
                .solicitud(null)
                .build();

        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }


    @Override
    public ObjectResponse<SolicitudProveedor> recordToEntityEdit(SolicitudProveedor entity, SolicitudProveedorRequest request) {
        return new ObjectResponse(Boolean.TRUE, null, null);
    }
}
