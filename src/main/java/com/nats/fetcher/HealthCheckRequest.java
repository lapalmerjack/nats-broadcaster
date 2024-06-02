package com.nats.fetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class HealthCheckRequest {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckRequest.class);


    @GetMapping("/health")
    public void readiness()  {

        logger.info("Broadcaster health check passed");

    }
}
