package sm.dsw.sgcp.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.maintenance.model.TipoInternamiento;

import java.util.List;

@Repository
public interface TipoInternamientoRepository extends JpaRepository<TipoInternamiento, Integer> {

    @Query("select t from TipoInternamiento t "
            + "where t.activo = true "
            + "order by t.id desc")
    List<TipoInternamiento> findByFilter();

}
