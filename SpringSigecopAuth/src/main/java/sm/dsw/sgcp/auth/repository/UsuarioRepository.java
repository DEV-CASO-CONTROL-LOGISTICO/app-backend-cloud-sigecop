package sm.dsw.sgcp.auth.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.dsw.sgcp.auth.model.Usuario;

/**
 *
 * @author Moises_F16.7.24
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.cuenta = :login and u.clave = :clave and u.activo = true")
    Usuario getUserForCredentials(@Param("login") String login, @Param("clave") String clave);

    @Query("select u from Usuario u "
            + "where u.activo = true and (:nombre is null or u.nombre like %:nombre%) "
            + "and (:apellidoPaterno is null or u.apellidoPaterno like %:apellidoPaterno%) "
            + "and (:apellidoMaterno is null or u.apellidoMaterno like %:apellidoMaterno%) "
            + "and (:rolId is null or u.rol.id = :rolId) "
            + "order by u.id desc")
    List<Usuario> findByFilter(
            @Param("nombre") String nombre,
            @Param("apellidoPaterno") String apellidoPaterno,
            @Param("apellidoMaterno") String apellidoMaterno,
            @Param("rolId") Integer rolId);

    @Query("select u from Usuario u " +
            "where u.proveedorId != null and u.proveedorId = :proveedorId and u.activo = true")
    List<Usuario> findByProveedor(
            @Param("proveedorId") Integer proveedorId
    );
}
