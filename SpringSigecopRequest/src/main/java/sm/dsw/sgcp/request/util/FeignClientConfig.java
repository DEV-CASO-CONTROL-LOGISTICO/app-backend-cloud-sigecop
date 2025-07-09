package sm.dsw.sgcp.request.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = getCurrentToken();

        if (token != null && !token.isEmpty()) {
            template.header("Authorization", "Bearer " + token);
        }
    }

    private String getCurrentToken() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION)
                    .replace("Bearer ", "");
        }
        return null;
    }
}

