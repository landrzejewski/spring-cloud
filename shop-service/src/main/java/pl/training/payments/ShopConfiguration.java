package pl.training.payments;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.adapters.ServiceResolver;
import pl.training.payments.adapters.events.PaymentEventDto;
import pl.training.payments.domain.OrderProcessor;
import pl.training.payments.ports.PaymentService;
import pl.training.payments.ports.ShopService;

import java.util.function.Consumer;

@EnableFeignClients
@Configuration
@Log
public class ShopConfiguration {

    @Bean
    public ShopService shopService(PaymentService paymentService) {
        return new OrderProcessor(paymentService);
    }

    public ShopConfiguration(@Value("${discount}") int discount, ServiceResolver serviceResolver) {
        log.info("Current discount: " + discount);
        log.info("Payments instance: " + serviceResolver.get("PAYMENTS-SERVICE"));
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

   /* @Bean
    public Consumer<PaymentEventDto> paymentEventsConsumer() {
        return event -> log.info("Payment status updated (id: %s)".formatted(event.getPaymentId()));
    }*/

}
