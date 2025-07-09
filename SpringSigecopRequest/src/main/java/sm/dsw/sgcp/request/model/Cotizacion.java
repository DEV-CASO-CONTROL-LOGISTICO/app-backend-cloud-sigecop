package sm.dsw.sgcp.request.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.AuditBase;

/**
 *
 * @author AlexChuky
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cotizacion", schema = "gestion")
public class Cotizacion extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "usuario_creacion_id")
    private Integer usuarioCreacionId;
    @JoinColumn(name = "usuario_estado_id")
    private Integer usuarioEstadoId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    private EstadoCotizacion estado;
    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;
    @Column(name = "fecha_emision")
    private Date fechaEmision;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "codigo")
    private String codigo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_proveedor_id", referencedColumnName = "id")
    private SolicitudProveedor solicitudProveedor;
}
