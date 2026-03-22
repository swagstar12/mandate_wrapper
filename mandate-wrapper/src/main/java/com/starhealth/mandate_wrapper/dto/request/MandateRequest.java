package com.starhealth.mandate_wrapper.dto.request;
import lombok.Data;
@Data
public class MandateRequest{
    private String orderId;
    private String amount;
    private String customer_id;
    private String mandate_id;
    private String execution_date;
    private String merchant_id;
    private String format;
}