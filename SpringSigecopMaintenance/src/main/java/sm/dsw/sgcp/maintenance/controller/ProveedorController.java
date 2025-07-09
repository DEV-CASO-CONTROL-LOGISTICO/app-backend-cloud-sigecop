package sm.dsw.sgcp.maintenance.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sm.dsw.sgcp.maintenance.dto.ProveedorRequest;
import sm.dsw.sgcp.maintenance.dto.ProveedorResponse;
import sm.dsw.sgcp.maintenance.service.ProveedorService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/proveedor")
@Validated
public class ProveedorController extends ControllerBase<ProveedorResponse, ProveedorRequest> {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService _proveedorService) {
        super(_proveedorService);
        this.proveedorService=_proveedorService;
    }
}
