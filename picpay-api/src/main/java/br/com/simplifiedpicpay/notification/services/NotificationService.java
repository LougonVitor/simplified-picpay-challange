package br.com.simplifiedpicpay.notification.services;

import br.com.simplifiedpicpay.notification.dto.NotificationRequestDto;
import br.com.simplifiedpicpay.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(String email, String message) throws Exception {
//        THE LOGIC WAS DISCUSSED BECAUSE THE API PROVIDED IN THE CHALLENGE IS NO LONGER WORKING!!!
//
//
//        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(email, message);
//
//        ResponseEntity<String> apiResponse = this.restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequestDto, String.class);
//
//        boolean isNotified = (apiResponse.getStatusCode() == HttpStatus.OK);
//        if(!isNotified) {
//            System.out.println("Error when sending notification");
//            throw new Exception("Notification service is down.");
//        }
        System.out.println("Completed!");
    }
}