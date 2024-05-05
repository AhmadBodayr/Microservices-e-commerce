package com.sessionservice.sessionservice.kafka;

import com.customerservice.customerservice.dto.CustomerSessionDto;
import com.productservice.productservice.dto.AddToCartMessage;
import com.sessionservice.sessionservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final SessionService sessionService;
    @KafkaListener(topics = "addToCart", groupId = "addToCart")
    public void addToCart(AddToCartMessage msg) {
        log.info(format("Consumed the message:: %s", msg.toString()));
        sessionService.addToCart(msg);
    }

    @KafkaListener(topics = "login", groupId = "login")
    public void login(CustomerSessionDto msg) {
        log.info(format("Consumed the customer session info:: %s", msg.toString()));
        sessionService.createSession(msg);
    }
}
