package lucas.payment_service.adapter.output.persistence;

import lucas.payment_service.domain.Payment;
import lucas.payment_service.domain.PaymentMethod;
import lucas.payment_service.domain.request.CreatePaymentRequest;

import java.time.LocalDateTime;

public class PaymentMapper {

    public static Payment toDomain(CreatePaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setMethod(PaymentMethod.fromString(request.getMethod()));
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdateAt(LocalDateTime.now());
        return payment;
    }

    public static PaymentEntity toEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setAmount(payment.getAmount());
        entity.setCurrency(payment.getCurrency());
        entity.setStatus(payment.getStatus());
        entity.setMethod(payment.getMethod());
        entity.setCreatedAt(payment.getCreatedAt());
        entity.setUpdateAt(payment.getUpdateAt());
        return entity;
    }
}
