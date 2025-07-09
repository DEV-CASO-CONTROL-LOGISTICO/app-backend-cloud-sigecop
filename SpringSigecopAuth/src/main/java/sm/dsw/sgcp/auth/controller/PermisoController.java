package sm.dsw.sgcp.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.auth.dto.PermisoRequest;
import sm.dsw.sgcp.auth.dto.PermisoResponse;
import sm.dsw.sgcp.auth.service.PermisoService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/permiso")
@Validated
public class PermisoController extends ControllerBase<PermisoResponse, PermisoRequest> {
    private final PermisoService permisoService;

    public PermisoController(PermisoService _permisoService) {
        super(_permisoService);
        this.permisoService=_permisoService;
    }

}
