package com.starhealth.mandate_wrapper.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class MandateRequest{
    @JsonProperty("order_id")
    @JsonAlias({"order_id", "orderId"})
    private String orderId;

    private String amount;

    @JsonProperty("customer_id")
    @JsonAlias({"customer_id", "customerId"})
    private String customerId;

    @JsonProperty("mandate_id")
    @JsonAlias({"mandate_id", "mandateId"})
    private String mandateId;

    @JsonProperty("execution_date")
    @JsonAlias({"execution_date", "executionDate"})
    private String executionDate;

    @JsonProperty("merchant_id")
    @JsonAlias({"merchant_id", "merchantId"})
    private String merchantId;

    private String format;
}
