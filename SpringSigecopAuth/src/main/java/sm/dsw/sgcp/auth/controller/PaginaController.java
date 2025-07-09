package sm.dsw.sgcp.auth.controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.auth.dto.PaginaRequest;
import sm.dsw.sgcp.auth.dto.PaginaResponse;
import sm.dsw.sgcp.auth.service.PaginaService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/pagina")
@Validated
public class PaginaController extends ControllerBase<PaginaResponse, PaginaRequest> {
    private final PaginaService paginaService;

    public PaginaController(PaginaService _paginaService) {
        super(_paginaService);
        this.paginaService=_paginaService;
    }

}