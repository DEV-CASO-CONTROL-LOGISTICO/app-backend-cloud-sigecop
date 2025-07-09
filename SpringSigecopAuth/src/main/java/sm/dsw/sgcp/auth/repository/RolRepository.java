package sm.dsw.sgcp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.repository.query.Param;
import sm.dsw.sgcp.auth.model.Rol;

/**
 *
 * @author Moises_F16.7.24
 */
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("select r from Rol r where r.activo = true "
            + "and (:nombre is null or r.nombre like %:nombre%) "
            + "order by r.id desc")
    List<Rol> findByFilter(@Param("nombre") String nombre);

}
