package pl.training.payments.adapters.payments;

import lombok.Data;

@Data
public class PaymentDto {

    private static final String STARTED_STATUS = "STARTED";

    private String status;

    public boolean isStarted() {
        return STARTED_STATUS.equalsIgnoreCase(status);
    }

}
