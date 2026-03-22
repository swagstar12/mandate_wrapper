package com.starhealth.mandate_wrapper.controller;
import com.starhealth.mandate_wrapper.dto.request.MandateRequest;
import com.starhealth.mandate_wrapper.dto.response.MandateResponse;
import com.starhealth.mandate_wrapper.service.MandateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/txns")
@RequiredArgsConstructor
public class MandateController{
    private final MandateService mandateService;

    @PostMapping("/present")
    public ResponseEntity<MandateResponse> presentMandate(@RequestBody MandateRequest request) {
        return ResponseEntity.ok(mandateService.callJuspay(request));

    }
}