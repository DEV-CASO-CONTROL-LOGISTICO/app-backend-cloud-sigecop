package sm.dsw.sgcp.request.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.request.dto.EstadoSolicitudRequest;
import sm.dsw.sgcp.request.dto.EstadoSolicitudResponse;
import sm.dsw.sgcp.request.service.EstadoSolicitudService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/estado_solicitud")
@Validated
public class EstadoSolicitudController extends ControllerBase<EstadoSolicitudResponse, EstadoSolicitudRequest> {

    private final EstadoSolicitudService estadoSolicitudService;

    public EstadoSolicitudController(EstadoSolicitudService _estadoSolicitudService) {
        super(_estadoSolicitudService);
        this.estadoSolicitudService = _estadoSolicitudService;
    }
}
