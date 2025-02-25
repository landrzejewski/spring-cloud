package pl.training.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdatedEvent {

    private String paymentId;
    private String oldStatus;
    private String newStatus;

}
