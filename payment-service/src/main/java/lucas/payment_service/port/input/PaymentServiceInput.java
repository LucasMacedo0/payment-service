package lucas.payment_service.port.input;


import lucas.payment_service.domain.request.CreatePaymentRequest;

public interface PaymentServiceInput {

    void createPayment(CreatePaymentRequest request);

}
