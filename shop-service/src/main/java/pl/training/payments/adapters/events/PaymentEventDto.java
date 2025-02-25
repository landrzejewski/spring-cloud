package pl.training.payments.adapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentEventDto {

    private final String paymentId;
    private final String type;
    private final String status;

}
