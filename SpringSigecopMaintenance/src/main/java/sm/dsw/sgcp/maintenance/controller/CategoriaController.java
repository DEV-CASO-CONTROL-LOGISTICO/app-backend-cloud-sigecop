package sm.dsw.sgcp.maintenance.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.maintenance.dto.CategoriaRequest;
import sm.dsw.sgcp.maintenance.dto.CategoriaResponse;
import sm.dsw.sgcp.maintenance.service.CategoriaService;
import sm.dsw.sgcp.util.base.ControllerBase;

@RestController
@RequestMapping(path = "api/v1/categoria")
@Validated
public class CategoriaController extends ControllerBase<CategoriaResponse, CategoriaRequest> {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService _categoriaService) {
        super(_categoriaService);
        this.categoriaService=_categoriaService;
    }
}
