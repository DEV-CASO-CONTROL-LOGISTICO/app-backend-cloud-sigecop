package sm.dsw.sgcp.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.dsw.sgcp.auth.dto.UsuarioRequest;
import sm.dsw.sgcp.auth.dto.UsuarioResponse;
import sm.dsw.sgcp.auth.service.UsuarioService;
import sm.dsw.sgcp.auth.util.JwtUtil;
import sm.dsw.sgcp.util.clase.ObjectResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Moises_F16.7.24
 */
@RestController
@RequestMapping(path = "api/v1/session")
@Validated
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest userRequest) {
        UsuarioResponse usuarioResponse = null;
        try {
            if (userRequest == null || !userRequest.isValidLogin()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Ingrese credenciales");
            }
            usuarioResponse = usuarioService.searchForCredentials(userRequest);
            if (usuarioResponse == null || usuarioResponse.getId() == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Credenciales no válidas");
            }
            return ResponseEntity.ok(
                    ObjectResponse.
                            <String>builder()
                            .success(Boolean.TRUE)
                            .object(jwtUtil.generarToken(usuarioResponse))
                            .build()
            );
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/getInfoSession")
    public ResponseEntity<?> getInfoSession() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Integer userId = (Integer) authentication.getPrincipal();
            UsuarioResponse usuarioResponse = usuarioService.searchInfoForId(userId);
            if (usuarioResponse == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
            return ResponseEntity.ok(usuarioResponse);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
