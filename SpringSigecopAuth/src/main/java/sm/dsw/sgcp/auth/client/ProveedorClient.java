package sm.dsw.sgcp.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sm.dsw.sgcp.auth.dto.ProveedorDTO;
import sm.dsw.sgcp.util.clase.RequestBase;

import java.util.List;

@FeignClient(name="SpringSigecopMaintenance",url="localhost:8080")
public interface ProveedorClient {
    @PostMapping("/api/v1/proveedor/find")
    ProveedorDTO findById(@RequestBody RequestBase course);
}
