package com.test.GatewayServer.repository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/message")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMessage")
    public String getMessage() {
        return restTemplate.getForObject("http://eurekaClient/message", String.class);
    }

    public String fallbackMessage(Throwable t){
        return "Fallback message";
    }
}
