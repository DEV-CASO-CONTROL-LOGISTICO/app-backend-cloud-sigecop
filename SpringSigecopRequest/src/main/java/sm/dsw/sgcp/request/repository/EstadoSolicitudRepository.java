package sm.dsw.sgcp.request.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.request.model.EstadoSolicitud;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Integer>{
    @Query("select e from EstadoSolicitud e "
            + "where e.activo = true "
            + "order by e.id desc")
    List<EstadoSolicitud> findByFilter();
}
