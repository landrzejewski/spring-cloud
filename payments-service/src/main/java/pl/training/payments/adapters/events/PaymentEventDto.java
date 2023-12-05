package pl.training.payments.adapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentEventDto {

    private String type;
    private String paymentId;

}
