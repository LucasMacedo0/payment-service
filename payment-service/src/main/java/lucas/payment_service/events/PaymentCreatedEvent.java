package lucas.payment_service.events;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCreatedEvent(
        UUID paymentId,
        BigDecimal amount,
        String currency
) {}