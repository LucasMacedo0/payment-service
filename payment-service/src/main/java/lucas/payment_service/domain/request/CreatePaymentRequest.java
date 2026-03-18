package lucas.payment_service.domain.request;

import lombok.Data;
import lucas.payment_service.domain.PaymentMethod;

import java.math.BigDecimal;

@Data
public class CreatePaymentRequest {

    private BigDecimal amount;
    private String currency;
    private PaymentMethod method;
    private String externalReference;
    private String idempotencyKey;

}