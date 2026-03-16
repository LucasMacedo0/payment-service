package lucas.payment_service.domain;

public enum PaymentStatus {
    CREATED,
    AUTHORIZED,
    CAPTURED,
    FAILED,
    CANCELLED;
}
