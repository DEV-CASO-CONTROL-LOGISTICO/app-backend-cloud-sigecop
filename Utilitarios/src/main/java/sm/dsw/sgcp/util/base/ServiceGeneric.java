package sm.dsw.sgcp.util.base;

import org.springframework.data.jpa.repository.JpaRepository;
import sm.dsw.sgcp.util.clase.AuditBase;
import sm.dsw.sgcp.util.clase.DtoGeneric;
import sm.dsw.sgcp.util.clase.ObjectResponse;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.util.List;
import java.util.Optional;

public abstract class ServiceGeneric<T extends DtoGeneric,F extends RequestBase,E extends AuditBase> implements ServiceBase<T, F>{

    private final Class<T> dtoClass;
    private final JpaRepository<E, Integer> repository;

    public ServiceGeneric(Class<T> dtoClass, JpaRepository<E, Integer> _repository) {
        this.dtoClass = dtoClass;
        this.repository = _repository;
    }

    @Override
    public List<T> list(F filter) {
        return convertReturnList(listBase(filter));
    }

    @Override
    public ObjectResponse<T> find(F request) {
        Optional<E> result = repository.findById(request.getId());
        if (!result.isPresent()) {
            return new ObjectResponse(Boolean.FALSE,"No se encontró el registro",null);
        }
        T object=convertReturnObject(result.get());
        ObjectResponse<T> responsePostFind=postFind(object);
        if(!responsePostFind.getSuccess()){
            return new ObjectResponse<>(Boolean.FALSE,"No se puedo completar la búsqueda de información",null);
        }
        return new ObjectResponse(Boolean.TRUE,null,responsePostFind.getObject());
    }

    @Override
    public ObjectResponse<T> delete(F request) {
        ObjectResponse validate=this.validateDelete(request);
        if(!validate.getSuccess()){
            return validate;
        }
        Optional<E> optionalRecord = repository.findById(request.getId());
        if (optionalRecord.isEmpty()) {
            return new ObjectResponse(Boolean.FALSE,"No se encontró el registro a eliminar",null);
        }
        E record = optionalRecord.get();
        record.setActivo(Boolean.FALSE);
        repository.save(record);
        return new ObjectResponse(Boolean.TRUE,null,convertReturnObject(record));
    }

    @Override
    public ObjectResponse<T> save(F request) {
        System.out.println("-----------------Service save");
        System.out.println(request);
        E record;
        ObjectResponse<E> resultConversion;
        if (request.getId() != null) {
            Optional<E> optionalRecord = repository.findById(request.getId());
            if (optionalRecord.isEmpty()) {
                return new ObjectResponse(Boolean.FALSE,"No se encontró el registro a editar",null);
            }
            resultConversion=recordToEntityEdit(optionalRecord.get(),request);
        } else {
            resultConversion=recordToEntityNew(request);
        }
        if(resultConversion.getSuccess()){
            record=resultConversion.getObject();
        }else{
            return new ObjectResponse<T>(Boolean.FALSE,resultConversion.getMessage(),null);
        }
        record = repository.save(record);
        ObjectResponse responsePostSave=postSave(request,record);
        if(!responsePostSave.getSuccess()){
            return responsePostSave;
        }
        return new ObjectResponse(Boolean.TRUE,null,convertReturnObject(record));
    }


    public ObjectResponse validateDelete(F request){
        return new ObjectResponse(Boolean.TRUE,null,null);
    }

    public ObjectResponse postSave(F request,E entity){
        return new ObjectResponse(Boolean.TRUE,null,null);
    }

    public ObjectResponse<T> postFind(T entity){
        return new ObjectResponse(Boolean.TRUE,null,entity);
    }

    public T convertReturnObject(E record){
        return T.fromEntity(record,dtoClass);
    }
    public List<T> convertReturnList(List<E> result){
        return T.fromEntities(result,dtoClass);
    }

    public abstract List<E> listBase(F filter);
    public abstract ObjectResponse<E> recordToEntityEdit(E entity,F request);
    public abstract ObjectResponse<E> recordToEntityNew(F request);
}

