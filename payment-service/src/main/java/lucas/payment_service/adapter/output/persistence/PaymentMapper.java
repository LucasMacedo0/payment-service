package lucas.payment_service.adapter.output.persistence;

import lucas.payment_service.domain.Payment;
import lucas.payment_service.domain.request.CreatePaymentRequest;

public class PaymentMapper {

    public static Payment toDomain(CreatePaymentRequest request) {

        Payment payment = new Payment();

        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setMethod(request.getMethod());
        payment.setExternalReference(request.getExternalReference());

        return payment;
    }

    public static PaymentEntity toEntity(Payment payment, String idempotencyKey) {

        PaymentEntity entity = new PaymentEntity();

        entity.setAmount(payment.getAmount());
        entity.setCurrency(payment.getCurrency());
        entity.setStatus(payment.getStatus());
        entity.setMethod(payment.getMethod());
        entity.setExternalReference(payment.getExternalReference());
        entity.setCreatedAt(payment.getCreatedAt());
        entity.setUpdatedAt(payment.getUpdatedAt());
        entity.setIdempotencyKey(idempotencyKey);

        return entity;
    }

}