package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.ports.PaymentService;

import java.net.URI;
import java.util.Currency;
import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class RestTemplatePaymentService implements PaymentService {

    private final RestTemplate restTemplate;

    @Value("${api.payments}")
    @Setter
    private URI paymentsUri;

    @Override
    public boolean pay(long amount, Currency currency) {
        var paymentRequestDto = new PaymentRequestDto("%d %s".formatted(amount, currency));
        try {
            var paymentDto = restTemplate.postForObject(paymentsUri, paymentRequestDto, PaymentDto.class);
            return Optional.ofNullable(paymentDto)
                    .map(PaymentDto::isStarted)
                    .orElse(false);
        } catch (RestClientException restClientException) {
            log.info("Payment failed: " + restClientException.getMessage());
        }
        return false;
    }

}
