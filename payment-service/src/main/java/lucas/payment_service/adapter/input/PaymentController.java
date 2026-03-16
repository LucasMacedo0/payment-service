package lucas.payment_service.adapter.input;

import lucas.payment_service.domain.Payment;
import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.port.input.PaymentServiceInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {


    private PaymentServiceInput serviceInput;

    @PostMapping("/payments")
    public ResponseEntity<Payment> payments(@RequestBody CreatePaymentRequest payment) {

        serviceInput.createPayment(payment);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
