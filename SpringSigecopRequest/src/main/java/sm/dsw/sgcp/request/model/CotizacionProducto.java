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
@Table(name = "cotizacion_producto")
/**
 *
 * @author jhochuq
 */
public class CotizacionProducto extends AuditBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cotizacion_id", referencedColumnName = "id")
    private Cotizacion cotizacion;
    @JoinColumn(name = "producto_id")
    private Integer productoId;
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Column(name = "cantidad_cotizada")
    private int cantidadCotizada;
    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

}
