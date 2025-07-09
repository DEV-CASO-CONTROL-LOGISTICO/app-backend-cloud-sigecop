package sm.dsw.sgcp.maintenance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.util.clase.AuditBase;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedor")
public class Proveedor extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;

}
