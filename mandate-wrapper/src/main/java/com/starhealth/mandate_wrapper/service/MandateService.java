package com.starhealth.mandate_wrapper.service;

import com.starhealth.mandate_wrapper.client.JuspayClient;
//import com.starhealth.mandate_wrapper.dto.*;
import com.starhealth.mandate_wrapper.dto.request.MandateRequest;
import com.starhealth.mandate_wrapper.dto.response.MandateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MandateService {
    private final JuspayClient juspayClient;

    public MandateResponse callJuspay(MandateRequest request){
        return juspayClient.callMandateAPI(request);
    }
}