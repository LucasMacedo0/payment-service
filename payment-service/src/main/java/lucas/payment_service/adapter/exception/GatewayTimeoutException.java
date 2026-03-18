package lucas.payment_service.adapter.exception;

public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException(String message) {
        super(message);
    }
}