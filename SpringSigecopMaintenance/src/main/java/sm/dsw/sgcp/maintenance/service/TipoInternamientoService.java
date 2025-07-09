package sm.dsw.sgcp.maintenance.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.maintenance.dto.TipoInternamientoRequest;
import sm.dsw.sgcp.maintenance.dto.TipoInternamientoResponse;
import sm.dsw.sgcp.maintenance.model.TipoInternamiento;
import sm.dsw.sgcp.maintenance.repository.TipoInternamientoRepository;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;

import java.util.List;

@Service
public class TipoInternamientoService extends ServiceGeneric<TipoInternamientoResponse, TipoInternamientoRequest, TipoInternamiento> {

    private final TipoInternamientoRepository tipoInternamientoRepository;

    public TipoInternamientoService(TipoInternamientoRepository _tipoInternamientoRepository) {
        super(TipoInternamientoResponse.class,_tipoInternamientoRepository);
        this.tipoInternamientoRepository = _tipoInternamientoRepository;
    }

    @Override
    public List<TipoInternamiento> listBase(TipoInternamientoRequest filter) {
        return tipoInternamientoRepository.findByFilter();
    }

    @Override
    public ObjectResponse<TipoInternamiento> recordToEntityEdit(TipoInternamiento entity, TipoInternamientoRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        return new ObjectResponse<>(Boolean.TRUE,null,entity);
    }

    @Override
    public ObjectResponse<TipoInternamiento> recordToEntityNew(TipoInternamientoRequest request) {
        return new ObjectResponse<>(Boolean.TRUE,null,new TipoInternamiento(
                request.getId(),
                request.getNombre(),
                request.getDescripcion(),
                Boolean.FALSE
        ));
    }

}
