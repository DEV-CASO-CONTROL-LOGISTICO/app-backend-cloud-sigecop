package sm.dsw.sgcp.request.service;


import java.util.List;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.request.dto.EstadoSolicitudRequest;
import sm.dsw.sgcp.request.dto.EstadoSolicitudResponse;
import sm.dsw.sgcp.request.model.EstadoSolicitud;
import sm.dsw.sgcp.request.repository.EstadoSolicitudRepository;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;


@Service
public class EstadoSolicitudService extends ServiceGeneric<EstadoSolicitudResponse, EstadoSolicitudRequest, EstadoSolicitud> {

    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public EstadoSolicitudService(EstadoSolicitudRepository _estadoSolicitudRepository) {
        super(EstadoSolicitudResponse.class, _estadoSolicitudRepository);
        this.estadoSolicitudRepository = _estadoSolicitudRepository;
    }

    @Override
    public List<EstadoSolicitud> listBase(EstadoSolicitudRequest filter) {
        return estadoSolicitudRepository.findByFilter();
    }

    @Override
    public ObjectResponse<EstadoSolicitud> recordToEntityEdit(EstadoSolicitud entity, EstadoSolicitudRequest request) {
        entity.setDescripcion(request.getDescripcion());
        entity.setDetalle(request.getDetalle());
        return new ObjectResponse<>(Boolean.TRUE, null, entity);
    }

    @Override
    public ObjectResponse<EstadoSolicitud> recordToEntityNew(EstadoSolicitudRequest request) {
        return new ObjectResponse<>(Boolean.TRUE, null, new EstadoSolicitud(
                null,
                request.getDescripcion(),
                request.getDetalle(),
                Boolean.FALSE
        ));
    }

}


