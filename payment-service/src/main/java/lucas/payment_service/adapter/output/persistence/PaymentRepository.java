package lucas.payment_service.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {


    boolean existsByIdempotencyKey(String idempotencyKey);
}