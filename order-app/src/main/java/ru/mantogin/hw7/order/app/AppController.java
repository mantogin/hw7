package ru.mantogin.hw7.order.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
public class AppController {

    private record Order(Integer accountNumber, Integer сost) {}

    @Autowired
    @Qualifier("billingRestClient")
    private RestClient billingRestClient;

    @Autowired
    @Qualifier("notificationsRestClient")
    private RestClient notificationsRestClient;

    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {

        System.out.printf("Order creation.");

        try {
            ResponseEntity<Void> response = billingRestClient
                    .put()
                    .uri("/billing/account/{number}", order.accountNumber)
                    .body(-order.сost)
                    .retrieve()
                    .toEntity(Void.class);
        } catch (Exception e) {
            System.out.printf("Order not created.");

            notificationsRestClient
                    .post()
                    .uri("/notifications/account/{number}", order.accountNumber)
                    .body("Order not created.")
                    .retrieve()
                    .toEntity(Void.class);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        System.out.printf("Order created.");

        notificationsRestClient
                .post()
                .uri("/notifications/account/{number}", order.accountNumber)
                .body("Order created.")
                .retrieve()
                .toEntity(Void.class);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
