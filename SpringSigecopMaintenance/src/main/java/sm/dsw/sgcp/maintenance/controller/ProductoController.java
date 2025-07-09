package sm.dsw.sgcp.maintenance.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.maintenance.dto.ProductoRequest;
import sm.dsw.sgcp.maintenance.dto.ProductoResponse;
import sm.dsw.sgcp.maintenance.service.ProductoService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/producto")
@Validated
public class ProductoController extends ControllerBase<ProductoResponse, ProductoRequest> {

    private final ProductoService productoService;

    public ProductoController(ProductoService _productoService) {
        super(_productoService);
        this.productoService=_productoService;
    }
}
