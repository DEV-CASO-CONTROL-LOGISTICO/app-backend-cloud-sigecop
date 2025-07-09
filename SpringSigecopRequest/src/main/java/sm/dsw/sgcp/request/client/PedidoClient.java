package sm.dsw.sgcp.request.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sm.dsw.sgcp.request.http.response.PedidoRequestDTO;
import sm.dsw.sgcp.request.http.response.ProductoDTO;
import sm.dsw.sgcp.request.util.FeignClientConfig;
import sm.dsw.sgcp.util.clase.RequestBase;

@FeignClient(name="SpringSigecopOrder",url="localhost:8080", configuration = FeignClientConfig.class)
public interface PedidoClient {
    @PostMapping("/api/v1/pedido/save")
    RequestBase save(@RequestBody PedidoRequestDTO course);
}