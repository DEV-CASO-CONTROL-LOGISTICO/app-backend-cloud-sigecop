package sm.dsw.sgcp.request.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.request.model.EstadoCotizacion;

/**
 *
 * @author Moises_F16.7.24
 */
@Repository
public interface EstadoCotizacionRepository extends JpaRepository<EstadoCotizacion, Integer> {

    @Query("select e from EstadoCotizacion e "
            + "where e.activo = true "
            + "order by e.id desc")
    List<EstadoCotizacion> findByFilter();

}
