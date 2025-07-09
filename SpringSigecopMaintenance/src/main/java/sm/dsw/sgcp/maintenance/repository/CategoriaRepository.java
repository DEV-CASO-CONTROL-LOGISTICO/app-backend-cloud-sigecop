package sm.dsw.sgcp.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.maintenance.model.Categoria;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c "
            + "where c.activo = true and (:nombre is null or c.nombre like %:nombre%) "
            + "order by c.id desc")
    List<Categoria> findByFilter(@Param("nombre") String nombre);

}
