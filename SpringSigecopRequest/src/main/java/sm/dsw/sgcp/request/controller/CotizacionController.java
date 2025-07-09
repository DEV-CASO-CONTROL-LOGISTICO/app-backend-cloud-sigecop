package sm.dsw.sgcp.request.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.request.dto.CotizacionRequest;
import sm.dsw.sgcp.request.dto.CotizacionResponse;
import sm.dsw.sgcp.request.service.CotizacionService;
import sm.dsw.sgcp.util.base.ControllerBase;
import sm.dsw.sgcp.util.clase.ObjectResponse;

/**
 *
 * @author Moises_F16.7.24
 */
@RestController
@RequestMapping(path = "api/v1/cotizacion")
@Validated
public class CotizacionController extends ControllerBase<CotizacionResponse, CotizacionRequest> {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService _cotizacionService) {
        super(_cotizacionService);
        this.cotizacionService = _cotizacionService;
    }

    @PostMapping("/aprobar")
    public ResponseEntity<?> aprobar(@RequestBody CotizacionRequest request) {
        try {
            ObjectResponse resultOperation = cotizacionService.aprobar(request);
            return resultOperation.getSuccess()
                    ? ResponseEntity.ok(resultOperation.getSuccess())
                    : ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultOperation.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/archivar")
    public ResponseEntity<?> archivar(@RequestBody CotizacionRequest request) {
        try {
            ObjectResponse resultOperation = cotizacionService.archivar(request);
            return resultOperation.getSuccess()
                    ? ResponseEntity.ok(resultOperation.getSuccess())
                    : ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultOperation.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
