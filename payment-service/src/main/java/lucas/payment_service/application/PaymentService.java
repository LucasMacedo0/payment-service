package lucas.payment_service.application;


import lucas.payment_service.adapter.output.persistence.PaymentMapper;
import lucas.payment_service.adapter.output.persistence.PaymentRepository;
import lucas.payment_service.domain.PaymentStatus;
import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.port.input.PaymentServiceInput;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentServiceInput {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void createPayment(CreatePaymentRequest request) {
        var payment = PaymentMapper.toDomain(request);
        payment.validateAmount(payment.getAmount());
        payment.setStatus(PaymentStatus.CREATED);
    
        var entity = PaymentMapper.toEntity(payment);

        paymentRepository.save(entity);
    }

}
