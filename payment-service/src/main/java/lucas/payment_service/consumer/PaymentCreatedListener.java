package lucas.payment_service.consumer;

import lucas.payment_service.application.PaymentProcessor;
import lucas.payment_service.events.PaymentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentCreatedListener {

    private final PaymentProcessor paymentProcessor;

    public PaymentCreatedListener(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    //executa em outra thread
    // request HTTP termina rápido
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // Disparar o evento depois do commit, evento só roda DEPOIS que salvou no banco
    public void handle(PaymentCreatedEvent event) {

        paymentProcessor.processPayment(event.paymentId()); // processa o pagamento vai autorizar ou falhar

    }
}