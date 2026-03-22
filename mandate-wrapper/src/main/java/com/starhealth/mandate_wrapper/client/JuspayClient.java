package com.starhealth.mandate_wrapper.client;

//import com.starhealth.mandate_wrapper.dto.*;
import com.starhealth.mandate_wrapper.dto.request.MandateRequest;
import com.starhealth.mandate_wrapper.dto.response.MandateResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JuspayClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.juspay.in/txns/present";
    //private final String AUTH_KEY = ""

    public MandateResponse callMandateAPI(MandateRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MandateRequest> entity = new HttpEntity<>(request,headers);

        ResponseEntity<MandateResponse> response =
                restTemplate.exchange(
                        URL,
                        HttpMethod.POST,
                        entity,
                        MandateResponse.class
                );
        return response.getBody();

    }
}