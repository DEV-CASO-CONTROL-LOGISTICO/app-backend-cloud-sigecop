package sm.dsw.sgcp.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.auth.dto.UsuarioRequest;
import sm.dsw.sgcp.auth.dto.UsuarioResponse;
import sm.dsw.sgcp.auth.service.UsuarioService;
import sm.dsw.sgcp.util.base.ControllerBase;

/**
 *
 * @author Moises_F16.7.24
 */
@RestController
@RequestMapping(path = "api/v1/usuario")
@Validated
public class UsuarioController extends ControllerBase<UsuarioResponse, UsuarioRequest> {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService _usuarioService) {
        super(_usuarioService);
        this.usuarioService=_usuarioService;
    }

}
