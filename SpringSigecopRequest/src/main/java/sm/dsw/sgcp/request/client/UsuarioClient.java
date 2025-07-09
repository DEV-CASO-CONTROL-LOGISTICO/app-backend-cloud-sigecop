package sm.dsw.sgcp.request.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sm.dsw.sgcp.request.http.response.UsuarioDTO;
import sm.dsw.sgcp.request.util.FeignClientConfig;
import sm.dsw.sgcp.util.clase.RequestBase;

@FeignClient(name="SpringSigecopAuth",url="localhost:8080", configuration = FeignClientConfig.class)
public interface UsuarioClient {
    @PostMapping("/api/v1/usuario/find")
    UsuarioDTO findById(@RequestBody RequestBase course);
}

