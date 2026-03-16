package lucas.payment_service.domain;

public enum PaymentMethod {

    CREDIT_CARD,
    PIX,
    DEBIT_CARD;

    private String name;

    public static PaymentMethod fromString(String name) {
        return switch(name.toUpperCase()){
            case "CREDIT_CARD" -> CREDIT_CARD;
            case "PIX" -> PIX;
            case "DEBIT_CARD" -> DEBIT_CARD;
            default -> throw new IllegalArgumentException(); //TODO tratar erro do tipo de pagamento!

        };
    }
}