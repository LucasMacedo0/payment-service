package lucas.payment_service.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Payment {

    private UUID id;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private PaymentMethod method;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String externalReference;

    public void validateAmount() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

    public void markAsCreated() {
        this.status = PaymentStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}