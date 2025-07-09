package sm.dsw.sgcp.maintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sm.dsw.sgcp.maintenance.dto.CategoriaRequest;
import sm.dsw.sgcp.maintenance.dto.CategoriaResponse;
import sm.dsw.sgcp.maintenance.model.Categoria;
import sm.dsw.sgcp.maintenance.model.Producto;
import sm.dsw.sgcp.maintenance.repository.CategoriaRepository;
import sm.dsw.sgcp.maintenance.repository.ProductoRepository;
import sm.dsw.sgcp.util.base.ServiceGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;

import java.util.List;

@Service
public class CategoriaService extends ServiceGeneric<CategoriaResponse, CategoriaRequest, Categoria> {


    private final CategoriaRepository categoriaRepository;
    @Autowired
    ProductoRepository productoRepository;

    public CategoriaService(CategoriaRepository _categoriaRepository) {
        super(CategoriaResponse.class,_categoriaRepository);
        this.categoriaRepository = _categoriaRepository;
    }

    @Override
    public List<Categoria> listBase(CategoriaRequest filter) {
        return categoriaRepository.findByFilter(filter.getNombre());
    }

    @Override
    public ObjectResponse<Categoria> recordToEntityEdit(Categoria entity, CategoriaRequest request) {
        entity.setNombre(request.getNombre());
        return new ObjectResponse<>(Boolean.TRUE,null,entity);
    }

    @Override
    public ObjectResponse<Categoria> recordToEntityNew(CategoriaRequest request) {
        return new ObjectResponse<>(Boolean.TRUE,null,new Categoria(
                request.getId(),
                request.getNombre()
        ));
    }

    @Override
    public ObjectResponse validateDelete(CategoriaRequest request){
        List<Producto> result=productoRepository.findByFilter(request.getId(),null);
        return (result==null || result.isEmpty())?
                new ObjectResponse(Boolean.TRUE,null,null):
                new ObjectResponse(Boolean.FALSE,"No puede eliminar mientras exista productos pertenecientes a esta categoría",null);
    }

}