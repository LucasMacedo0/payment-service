package lucas.payment_service.application;


import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lucas.payment_service.adapter.output.persistence.PaymentEntity;
import lucas.payment_service.adapter.output.persistence.PaymentMapper;
import lucas.payment_service.adapter.output.persistence.PaymentRepository;
import lucas.payment_service.domain.PaymentStatus;
import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.events.PaymentEventPublisher;
import lucas.payment_service.port.input.PaymentServiceInput;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService implements PaymentServiceInput {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher eventPublisher;

    public PaymentService(PaymentRepository paymentRepository, PaymentEventPublisher eventPublisher) {
        this.paymentRepository = paymentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    @Override   // apenas registra que o pagamento foi criado mas não processa o pagamento
    public void createPayment(CreatePaymentRequest request) {
        validateIdempotency(request);
        var payment = PaymentMapper.toDomain(request);
        payment.validateAmount();
        payment.markAsCreated();

        var entity = paymentRepository.save(
                PaymentMapper.toEntity(payment, request.getIdempotencyKey())
        );

        payment.setId(entity.getId());

        eventPublisher.publishPaymentCreated(payment); // um evento do tipo "pagamento foi criado" não decide o que fazer, só avisa.
    }

    @Transactional
    public void authorizePayment(UUID paymentId) {

        var entity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalStateException("Payment not found"));

        if (isNotInCreatedState(entity)) return;

        entity.setStatus(PaymentStatus.AUTHORIZED);

        paymentRepository.save(entity);
    }

    @Transactional
    public void failPayment(UUID paymentId) {

        var entity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalStateException("Payment not found"));

        if (isNotInCreatedState(entity)) return;

        entity.setStatus(PaymentStatus.FAILED);

        paymentRepository.save(entity);
    }

    private static boolean isNotInCreatedState(PaymentEntity entity) {
        return entity.getStatus() != PaymentStatus.CREATED;
    }

    private static boolean validateStatus(PaymentEntity entity) {
        if (entity.getStatus() != PaymentStatus.CREATED) {
            return true;
        }
        return false;
    }

    private void validateIdempotency(CreatePaymentRequest request) {

        if (StringUtils.isEmpty(request.getIdempotencyKey())) {
            throw new IllegalStateException("idempotencyKey is required");
        }

        if (paymentRepository.existsByIdempotencyKey(request.getIdempotencyKey())) {
            throw new IllegalStateException("Pagamento já processado");
        }
    }


}
