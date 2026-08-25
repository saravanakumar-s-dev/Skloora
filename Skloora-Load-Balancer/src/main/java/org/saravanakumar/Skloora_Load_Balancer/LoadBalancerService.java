package org.saravanakumar.Skloora_Load_Balancer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoadBalancerService {

    private final RestClient restClient = RestClient.create();

    private final List<String> servers = List.of(
            "http://localhost:8081",
            "http://localhost:8082"
    );

    private final AtomicInteger currentServer = new AtomicInteger(0);

    public ResponseEntity<byte[]> forwardRequest(
            HttpServletRequest request) throws IOException {

        String server = getNextServer();

        String targetUrl = buildTargetUrl(server, request);

        System.out.println("=================================");
        System.out.println("Incoming URI : " + request.getRequestURI());
        System.out.println("Selected Server : " + server);
        System.out.println("Target URL : " + targetUrl);
        System.out.println("=================================");

        HttpHeaders headers = copyRequestHeaders(request);

        byte[] body = request.getInputStream().readAllBytes();

        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        return restClient
                .method(method)
                .uri(targetUrl)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(body)
                .retrieve()
                .toEntity(byte[].class);
    }
    private String getNextServer() {

        int index = Math.floorMod(
                currentServer.getAndIncrement(),
                servers.size()
        );

        return servers.get(index);
    }

    private String buildTargetUrl(
            String server,
            HttpServletRequest request) {

        String uri = request.getRequestURI();

        String query = request.getQueryString();

        if (query != null && !query.isBlank()) {
            uri += "?" + query;
        }

        return server + uri;
    }

    private HttpHeaders copyRequestHeaders(
            HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();

        Enumeration<String> headerNames =
                request.getHeaderNames();

        while (headerNames != null &&
                headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();

            /*
             * These are controlled by the proxy/client
             * and should not simply be forwarded.
             */
            if (isHopByHopHeader(headerName)) {
                continue;
            }

            Enumeration<String> values =
                    request.getHeaders(headerName);

            while (values.hasMoreElements()) {

                headers.add(
                        headerName,
                        values.nextElement()
                );
            }
        }

        return headers;
    }

    private boolean isHopByHopHeader(String header) {

        return header.equalsIgnoreCase("Host")
                || header.equalsIgnoreCase("Connection")
                || header.equalsIgnoreCase("Keep-Alive")
                || header.equalsIgnoreCase("Proxy-Authenticate")
                || header.equalsIgnoreCase("Proxy-Authorization")
                || header.equalsIgnoreCase("TE")
                || header.equalsIgnoreCase("Trailer")
                || header.equalsIgnoreCase("Transfer-Encoding")
                || header.equalsIgnoreCase("Upgrade")
                || header.equalsIgnoreCase("Content-Length");
    }


}
