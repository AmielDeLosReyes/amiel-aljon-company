package amiel_aljon.auth_server.config.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("================================     HTTP REQUEST START     ================================");
        log.info("URI            ::  {}", request.getURI());
        log.info("METHOD         ::  {}", request.getMethod());
        log.debug("HEADERS        ::  {}", request.getHeaders());
        String bodyText = new String(body, StandardCharsets.UTF_8);
        if (bodyText.length() > 2000) {
            bodyText = bodyText.substring(0, 2000) + ".....";
        }
        log.debug("REQUEST BODY   ::  {}", bodyText);
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        log.info("================================          RESPONSE          ================================");
        log.info("STATUS CODE    ::  {}", response.getStatusCode());
        log.debug("STATUS TEXT    ::  {}", response.getStatusText());
        log.debug("HEADERS        ::  {}", response.getHeaders());
        String bodyText = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
        if (bodyText.length() > 5000) {
            bodyText = bodyText.substring(0, 5000) + ".....";
        }
        log.debug("RESPONSE BODY  ::  {}", bodyText);
        log.info("================================      HTTP REQUEST END      ================================");
    }
}
