package com.starhealth.mandate_wrapper.client;

import com.starhealth.mandate_wrapper.dto.request.MandateRequest;
import com.starhealth.mandate_wrapper.dto.response.MandateResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.util.Base64;

@Component
public class JuspayClient {

    @Value("${juspay.api.url}")
    private String url;

    @Value("${juspay.api.key}")
    private String apiKey;

    private RestTemplate buildRestTemplate() throws Exception {
        // Trust all certificates (use only in dev/sandbox)
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(
                    PoolingHttpClientConnectionManagerBuilder.create()
                        .setSSLSocketFactory(
                            SSLConnectionSocketFactoryBuilder.create()
                                .setSslContext(sslContext)
                                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .build()
                        )
                        .build()
                )
                .build();

        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

    public MandateResponse callMandateAPI(MandateRequest request) {
        try {
            RestTemplate restTemplate = buildRestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Basic Auth: Base64(apiKey + ":")
            String credentials = apiKey + ":";
            String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
            headers.set("Authorization", "Basic " + encoded);

            MultiValueMap<String, String> formBody = new LinkedMultiValueMap<>();
            if (request.getOrderId() != null)       formBody.add("order_id", request.getOrderId());
            if (request.getAmount() != null)         formBody.add("amount", request.getAmount());
            if (request.getCustomerId() != null)     formBody.add("customer_id", request.getCustomerId());
            if (request.getMandateId() != null)      formBody.add("mandate_id", request.getMandateId());
            if (request.getExecutionDate() != null)  formBody.add("execution_date", request.getExecutionDate());
            if (request.getMerchantId() != null)     formBody.add("merchant_id", request.getMerchantId());
            if (request.getFormat() != null)         formBody.add("format", request.getFormat());

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formBody, headers);

            ResponseEntity<MandateResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    MandateResponse.class
            );

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to call Juspay API: " + e.getMessage(), e);
        }
    }
}
