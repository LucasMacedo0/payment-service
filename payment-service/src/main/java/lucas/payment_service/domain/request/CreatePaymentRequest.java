package lucas.payment_service.domain.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentRequest {
    private BigDecimal amount;
    private String currency;
    private String status;
    private String method;
}