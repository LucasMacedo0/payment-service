package lucas.payment_service.application;

import lombok.extern.slf4j.Slf4j;
import lucas.payment_service.adapter.exception.GatewayTimeoutException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class PaymentProcessor {

    private final PaymentService paymentService;

    public PaymentProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Retryable(
            value = { GatewayTimeoutException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public void processPayment(UUID paymentId) {

        int scenario = new Random().nextInt(3);

        if (scenario == 0) {
            paymentService.authorizePayment(paymentId);
            return;
        }

        if (scenario == 1) {
            throw new GatewayTimeoutException("Timeout no gateway");
        }

        // recusado → sem retry
        paymentService.failPayment(paymentId);
    }

    @Recover
    public void recover(GatewayTimeoutException ex, UUID paymentId) {

        log.error("Falhou depois de 3 tentativas para paymentId={}", paymentId, ex);

        paymentService.failPayment(paymentId);
    }
}