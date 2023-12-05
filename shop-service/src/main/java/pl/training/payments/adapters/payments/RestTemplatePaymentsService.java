package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.ports.PaymentsService;

import java.net.URI;
import java.util.Optional;

@Primary
@Component
@Log
@RequiredArgsConstructor
public class RestTemplatePaymentsService implements PaymentsService {

    private static final String PAYMENTS_SERVICE = "PAYMENTS-SERVICE";
    private static final String PAYMENTS_ENDPOINT = "/payments";

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Value("${api.payments}")
    @Setter
    private URI paymentsApi;

    @Override
    public boolean pay(long amount, String currency) {
        var paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setValue("%d %s".formatted(amount, currency));
        try {
            var paymentDto = restTemplate.postForObject(/*getPaymentsUri()*/paymentsApi, paymentRequestDto, PaymentDto.class);
            return Optional.ofNullable(paymentDto)
                    .map(payment -> payment.status.equals("STARTED"))
                    .orElse(false);
        } catch (RestClientException restClientException) {
            log.info("Payment failed: " + restClientException.getMessage());
        }
        return false;
    }

    private URI getPaymentsUri() {
        var instances = discoveryClient.getInstances(PAYMENTS_SERVICE);
        var instance = instances.stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        return instance.getUri().resolve(PAYMENTS_ENDPOINT);
    }

}
