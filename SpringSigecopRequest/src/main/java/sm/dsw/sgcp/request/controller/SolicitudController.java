package sm.dsw.sgcp.request.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.request.dto.SolicitudProveedorRequest;
import sm.dsw.sgcp.request.dto.SolicitudRequest;
import sm.dsw.sgcp.request.dto.SolicitudResponse;
import sm.dsw.sgcp.request.service.SolicitudService;
import sm.dsw.sgcp.util.base.ControllerBase;
import sm.dsw.sgcp.util.clase.ObjectResponse;

@RestController
@RequestMapping(path = "api/v1/solicitud")
@Validated
public class SolicitudController extends ControllerBase<SolicitudResponse, SolicitudRequest> {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService _solicitudService) {
        super(_solicitudService);
        this.solicitudService = _solicitudService;
    }

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizar(@RequestBody SolicitudRequest request) {
        try {
            ObjectResponse resultOperation = solicitudService.finalizar(request);
            return resultOperation.getSuccess()
                    ? ResponseEntity.ok(resultOperation.getSuccess())
                    : ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultOperation.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/SolicitudProveedor")
    public ResponseEntity<?> listSolicitudByProveedor(@RequestBody SolicitudProveedorRequest request) {
        try {
            return ResponseEntity.ok(solicitudService.listSolicitudByProveedor(request));
        } catch (Exception e) {
            //loggerBase.error("Error list "+this.getClass().getName(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

