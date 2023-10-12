package com.example.fcm.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DemoController {

    private final FirebaseMessaging fcm;

    public DemoController(FirebaseMessaging fcm) {
        this.fcm = fcm;
    }

    @PostMapping("send-noti/{token}")
    public ResponseEntity<?> sendMsg(@PathVariable String token) throws FirebaseMessagingException {

        Message msg = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle("Title")
                        .setBody("Hello World")
                        .setImage("https://<message url here>")
                        .build())
                .build();
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("firebaseResponse", fcm.send(msg));
        return ResponseEntity.ok(responseMap);
    }

    /**
     * Dr pl, Token ka backend ka nay win pay loh m ya wo, so frontend side needs to get that token and sends it to you,
     * and then you can use that token and call API as above
     *
     */
}
