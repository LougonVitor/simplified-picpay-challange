package br.com.simplifiedpicpay.transaction.infrastructure.adapter;

import br.com.simplifiedpicpay.transaction.application.ports.IAuthorizationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ExternalAuthorizationAdapter implements IAuthorizationPort {

    @Autowired
    private RestTemplate restTemplate;

    private static final String AUTH_URL = "https://util.devi.tools/api/v2/authorize";

    @Override
    public boolean isAuthorized(Long senderId, BigDecimal amount) {
        try {

            // In real APIs, we would send the senderId or a Token in the headers.
            // For this simulated API, we only check the status of the GET response.
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(AUTH_URL, Map.class);

            // Returns true if the http status is 200 OK
            return authorizationResponse.getStatusCode() == HttpStatus.OK;

        } catch (Exception e) {
            System.err.println("Erro ao consultar serviço de autorização: " + e.getMessage());
            return false;
        }
    }
}
