package lucas.payment_service.adapter.input;

import lucas.payment_service.domain.Payment;
import lucas.payment_service.domain.request.CreatePaymentRequest;
import lucas.payment_service.domain.response.PaymentResponse;
import lucas.payment_service.port.input.PaymentServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceInput serviceInput;

    @PostMapping("/payments")
    public ResponseEntity<Payment> payments(@RequestBody CreatePaymentRequest payment) {

        serviceInput.createPayment(payment);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentResponse> payments(@PathVariable UUID id) {
        var payment = serviceInput.getPaymentById(id);

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }


}
