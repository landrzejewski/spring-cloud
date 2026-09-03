package pl.training.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusUpdatedEvent {

    private String paymentId;
    private String oldStatus;
    private String newStatus;

}
