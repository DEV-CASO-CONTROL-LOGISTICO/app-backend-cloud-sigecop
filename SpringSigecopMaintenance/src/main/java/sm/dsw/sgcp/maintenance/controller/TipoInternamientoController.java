package sm.dsw.sgcp.maintenance.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.maintenance.dto.TipoInternamientoRequest;
import sm.dsw.sgcp.maintenance.dto.TipoInternamientoResponse;
import sm.dsw.sgcp.maintenance.service.TipoInternamientoService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/tipoInternamiento")
@Validated
public class TipoInternamientoController extends ControllerBase<TipoInternamientoResponse, TipoInternamientoRequest> {

    private final TipoInternamientoService tipoInternamientoService;

    public TipoInternamientoController(TipoInternamientoService _tipoInternamientoService) {
        super(_tipoInternamientoService);
        this.tipoInternamientoService=_tipoInternamientoService;
    }
}
