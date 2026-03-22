package com.starhealth.mandate_wrapper.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public  class MandateResponse {
    private String status;

    @JsonProperty("txn_uuid")
    private String txnUuid;

    @JsonProperty("offer_details")
    private OfferDetails offerDetails;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("txn_id")
    private String txnId;

    @Data
    public static class OfferDetails{
        private List<Object> offers;
    }
}
