package pl.training.payments.adapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.training.payments.domain.PaymentStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEventDto {

    private String paymentId;
    private String type;

}
