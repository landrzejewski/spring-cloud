package pl.training.payments.adapters.payments;

import lombok.Data;

import java.time.Instant;

@Data
public class PaymentDto {

    String id;
    String value;
    Instant timestamp;
    String status;

}
