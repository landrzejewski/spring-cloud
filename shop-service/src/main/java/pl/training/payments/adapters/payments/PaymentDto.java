package pl.training.payments.adapters.payments;

import lombok.Data;

import java.time.Instant;

@Data
public class PaymentDto {

    private static final String STARTED_STATUS = "STARTED";

    /*private String id;
    private String value;
    private Instant timestamp;*/
    private String status;

    public boolean isStarted() {
        return status.equals(STARTED_STATUS);
    }

}
