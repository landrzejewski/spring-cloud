package pl.training.payments.adapters.events;

import lombok.Data;

@Data
public class PaymentEventDto {

    private String paymentId;
    private String type;

}
