package sm.dsw.sgcp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.auth.model.Pagina;
import java.util.List;

/**
 *
 * @author Moises_F16.7.24
 */
@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Integer> {

    @Query("select pe.pagina from Permiso pe " +
            "where pe.rol.id = :rolId and pe.activo = true " +
            "and pe.pagina.activo = true")
    List<Pagina> listForRol(@Param("rolId") Integer rolId);

    @Query("select pa from Pagina pa where pa.activo = true "
            + "and (:nombre is null or pa.nombre like %:nombre%) "
            + "order by pa.id desc")
    List<Pagina> findByFilter(@Param("nombre") String nombre);

}
