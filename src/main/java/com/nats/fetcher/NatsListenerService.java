package com.nats.fetcher;



import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class NatsListenerService {

    private static final Logger logger = LoggerFactory.getLogger(NatsListenerService.class);


    @Value("${nats.url}")
    private String natsUrl;


    @Value("${nats.subject}")
    private String natsSubject;


    @PostConstruct
    public void init() {
        try {
            Connection natsConnection = Nats.connect(natsUrl);
            Dispatcher dispatcher = natsConnection.createDispatcher(msg -> {
                String message = new String(msg.getData());
                logger.info("Received message: {}", message);

            });
            dispatcher.subscribe(natsSubject);
            logger.info("Subscribed to NATS subject: {}", natsSubject);
        } catch (IOException | InterruptedException e) {
            logger.error("Failed to connect to NATS server", e);
            throw new RuntimeException("Failed to connect to NATS server", e);
        }
    }




}
