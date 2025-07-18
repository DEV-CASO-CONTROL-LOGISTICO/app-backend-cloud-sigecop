package sm.dsw.sgcp.request.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sm.dsw.sgcp.request.http.response.ProveedorDTO;
import sm.dsw.sgcp.request.util.FeignClientConfig;
import sm.dsw.sgcp.util.clase.RequestBase;

@FeignClient(name="SpringSigecopMaintenanceProveedor",url="localhost:8080", configuration = FeignClientConfig.class)
public interface ProveedorClient {
    @PostMapping("/api/v1/proveedor/find")
    ProveedorDTO findById(@RequestBody RequestBase course);
}
