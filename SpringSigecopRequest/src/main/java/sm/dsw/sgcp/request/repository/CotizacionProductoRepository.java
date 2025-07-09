package sm.dsw.sgcp.request.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.request.model.CotizacionProducto;

/**
 *
 * @author Moises_F16.7.24
 */
@Repository
public interface CotizacionProductoRepository extends JpaRepository<CotizacionProducto, Integer> {

    @Query("select cp from CotizacionProducto cp "
            + "where cp.activo = true "
            + "and (:cotizacionId is null or cp.cotizacion.id = :cotizacionId) "
            + "order by cp.id desc")
    List<CotizacionProducto> findByFilter(@Param("cotizacionId") Integer cotizacionId);
}
