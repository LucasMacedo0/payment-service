package lucas.payment_service.port.input;


import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.domain.response.PaymentResponse;

import java.util.UUID;

public interface PaymentServiceInput {

    void createPayment(CreatePaymentRequest request);

    PaymentResponse getPaymentById(UUID id);
}
