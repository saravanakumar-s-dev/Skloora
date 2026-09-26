<<<<<<< HEAD
package org.saravanakumar.Skloora_Load_Balancer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProxyController {

    private final LoadBalancerService loadBalancerService;

    public ProxyController(LoadBalancerService loadBalancerService) {
        this.loadBalancerService = loadBalancerService;
    }

    @RequestMapping("/**")
    public ResponseEntity<byte[]> proxy(HttpServletRequest request)
            throws IOException {

        return loadBalancerService.forwardRequest(request);
    }
=======
package org.saravanakumar.Skloora_Load_Balancer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProxyController {

    private final LoadBalancerService loadBalancerService;

    public ProxyController(LoadBalancerService loadBalancerService) {
        this.loadBalancerService = loadBalancerService;
    }

    @RequestMapping("/**")
    public ResponseEntity<byte[]> proxy(HttpServletRequest request)
            throws IOException {

        return loadBalancerService.forwardRequest(request);
    }
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
}