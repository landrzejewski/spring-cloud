package pl.training.payments.adapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentUpdatedMessage {

    private String paymentId;
    private String status;
    private String type;

}
