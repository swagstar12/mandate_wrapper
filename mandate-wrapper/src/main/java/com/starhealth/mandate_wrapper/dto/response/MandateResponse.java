package com.starhealth.mandate_wrapper.dto.response;
import lombok.Data;

import java.util.List;

@Data
public  class MandateResponse {
    private String status;
    private String txn_uuid;
    private OfferDetails offer_details;
    private String order_id;
    private String txn_id;

    @Data
    public static class OfferDetails{
        private List<Object> offers;
    }
}