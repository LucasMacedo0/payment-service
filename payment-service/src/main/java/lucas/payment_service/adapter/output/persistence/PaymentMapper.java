package lucas.payment_service.adapter.output.persistence;

import lucas.payment_service.domain.Payment;
import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.domain.response.PaymentResponse;

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

    public static PaymentResponse toResponse(PaymentEntity entity) {

        PaymentResponse response = new PaymentResponse();

        response.setId(entity.getId());
        response.setAmount(entity.getAmount());
        response.setCurrency(entity.getCurrency());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;
    }
}