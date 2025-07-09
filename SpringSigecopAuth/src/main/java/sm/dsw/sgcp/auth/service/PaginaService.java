package sm.dsw.sgcp.auth.service;


import org.springframework.stereotype.Service;
import sm.dsw.sgcp.auth.dto.PaginaRequest;
import sm.dsw.sgcp.auth.dto.PaginaResponse;
import sm.dsw.sgcp.auth.model.Pagina;
import sm.dsw.sgcp.auth.repository.PaginaRepository;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;

import java.util.List;

@Service
public class PaginaService extends ServiceGeneric<PaginaResponse, PaginaRequest, Pagina> {

    private final PaginaRepository paginaRepository;

    public PaginaService(PaginaRepository _paginaRepository) {
        super(PaginaResponse.class,_paginaRepository);
        this.paginaRepository = _paginaRepository;
    }

    @Override
    public List<Pagina> listBase(PaginaRequest filter) {
        return paginaRepository.findByFilter(filter.getNombre());
    }

    @Override
    public ObjectResponse<Pagina> recordToEntityEdit(Pagina entity, PaginaRequest request) {
        entity.setNombre(request.getNombre());
        entity.setUrl(request.getUrl());
        return new ObjectResponse<>(Boolean.TRUE,null,entity);
    }

    @Override
    public ObjectResponse<Pagina> recordToEntityNew(PaginaRequest request) {
        return new ObjectResponse<>(Boolean.TRUE,null,new Pagina(
                request.getId(),
                request.getNombre(),
                request.getUrl()
        ));
    }


}
