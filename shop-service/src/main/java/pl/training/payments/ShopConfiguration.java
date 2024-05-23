package pl.training.payments;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.domain.ConstantDiscountCalculator;
import pl.training.payments.domain.OrderProcessor;
import pl.training.payments.ports.PaymentService;
import pl.training.payments.ports.ShopService;

@EnableFeignClients
@Configuration
@Log
public class ShopConfiguration {

    @Bean
    public ShopService shopService(PaymentService paymentService, ConstantDiscountCalculator discountCalculator) {
        return new OrderProcessor(paymentService, discountCalculator);
    }

    // @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

   /* @Bean
    public Consumer<PaymentEventDto> paymentEventsConsumer() {
        return event -> log.info("Payment status updated (id: %s)".formatted(event.getPaymentId()));
    }*/

    @RefreshScope
    @Bean
    public ConstantDiscountCalculator discountCalculator(@Value("${discount}") long discount) {
        log.info("DiscountCalculator created...");
        return new ConstantDiscountCalculator(discount);
    }

}
