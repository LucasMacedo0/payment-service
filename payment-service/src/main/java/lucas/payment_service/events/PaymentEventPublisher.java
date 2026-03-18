package lucas.payment_service.events;

import lucas.payment_service.domain.Payment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {

    private final ApplicationEventPublisher publisher;


    public PaymentEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;

    }

    public void publishPaymentCreated(Payment payment) {
        publisher.publishEvent(new PaymentCreatedEvent(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency()
        ));
    }
}
