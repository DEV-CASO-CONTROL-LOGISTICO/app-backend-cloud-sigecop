package sm.dsw.sgcp.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.auth.dto.RolRequest;
import sm.dsw.sgcp.auth.dto.RolResponse;
import sm.dsw.sgcp.auth.service.RolService;
import sm.dsw.sgcp.util.base.ControllerBase;

/**
 *
 * @author Moises_F16.7.24
 */
@RestController
@RequestMapping(path = "api/v1/rol")
@Validated
public class RolController extends ControllerBase<RolResponse, RolRequest> {

    private final RolService rolService;

    public RolController(RolService _rolService) {
        super(_rolService);
        this.rolService=_rolService;
    }
}
