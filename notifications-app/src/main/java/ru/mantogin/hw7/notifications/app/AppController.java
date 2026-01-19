package ru.mantogin.hw7.notifications.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AppController {

    private Map<Integer, String> notifications = new HashMap<>();

    @PostMapping(value = "/notifications/account/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createAccountNotification(@PathVariable Integer number, @RequestBody String notification) {

        System.out.printf("Create account notification.");

        notifications.put(number, notification);

        System.out.printf("notification: " + notification + " for account: " + number + " added.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/notifications/account/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAccountNotification(@PathVariable Integer number) {

        System.out.printf("Get account notification.");

        String notification = notifications.get(number);

        System.out.printf("notification: " + notification + " for account: " + number + " received.");

        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
