package lucas.payment_service.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Payment {

    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private PaymentMethod method;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public void validateAmount(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido"); //TODO tratar essa exception!
        }

    }

}
