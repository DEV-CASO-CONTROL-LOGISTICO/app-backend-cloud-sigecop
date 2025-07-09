package sm.dsw.sgcp.util.base;

import sm.dsw.sgcp.util.clase.ObjectResponse;

import java.util.List;

public interface ServiceBase <T,F>{

    public List<T> list(F filter);

    public ObjectResponse<T> find(F filter);

    public ObjectResponse<T> save(F filter);

    public ObjectResponse<T> delete(F filter);

}
