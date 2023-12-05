package pl.training.payments.adapters.payments;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.training.payments.ports.PaymentsService;

import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class FeignPaymentsService implements PaymentsService {

    private final PaymentsApi paymentsApi;

    public boolean pay(long amount, String currency) {
        var paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setValue("%d %s".formatted(amount, currency));
        try {
            var paymentDto = paymentsApi.pay(paymentRequestDto);
            return Optional.ofNullable(paymentDto)
                    .map(payment -> payment.status.equals("STARTED"))
                    .orElse(false);
        } catch (FeignException.FeignClientException feignClientException) {
            log.info("Payment failed: " + feignClientException.getMessage());
        }
        return false;
    }

}
