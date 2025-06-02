package com.example.demo.controller;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Security;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private Subscription subscription; // Chỉ demo nên lưu tạm

    @PostConstruct
    public void init() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Subscription subscription) {
        this.subscription = subscription;
        return ResponseEntity.ok("Subscribed");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestParam String title, @RequestParam String body) throws Exception {
        if (subscription == null) return ResponseEntity.badRequest().body("No subscription");

        Notification notification = new Notification(subscription, new Gson().toJson(Map.of(
                "title", title,
                "body", body
        )));

        PushService pushService = new PushService()
                .setPublicKey("BJysKMjkq86zvJHWK5uDoNECcta_MMD0Y-9kTCvep7VRhk2MVKSu1ULfsGaiUDcTdh9_xoxMoQdccly4Ba5Cusc")
                .setPrivateKey("8PsULdD_qb0Mgg8JK6_cXiJ5vVmELMBRnUezbv2ZAys")
                .setSubject("mailto: <huandt.19@grad.uit.edu.vn");

        pushService.send(notification);
        System.out.println("👉 Subscription endpoint: " + subscription.endpoint);
        System.out.println("👉 Sending push to browser...");
        return ResponseEntity.ok("Notification sent");
    }
}
