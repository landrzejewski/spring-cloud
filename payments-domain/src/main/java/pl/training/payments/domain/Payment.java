package pl.training.payments.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Builder
@Value
public class Payment {

    String id;
    Money value;
    Instant timestamp;
    @With
    PaymentStatus status;

}
