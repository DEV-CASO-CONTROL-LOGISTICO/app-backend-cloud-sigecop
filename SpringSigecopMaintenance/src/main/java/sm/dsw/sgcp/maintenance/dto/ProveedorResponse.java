package sm.dsw.sgcp.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sm.dsw.sgcp.maintenance.model.Proveedor;
import sm.dsw.sgcp.util.clase.DtoGeneric;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponse extends DtoGeneric<Proveedor,ProveedorResponse> {

    private Integer id;
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private String correo;

    @Override
    protected ProveedorResponse mapEntityToDto(Proveedor entity, ProveedorResponse dto) {
        dto.setId(entity.getId());
        dto.setRuc(entity.getRuc());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setNombreComercial(entity.getNombreComercial());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setCorreo(entity.getCorreo());
        return dto;
    }

}
