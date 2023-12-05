package pl.training.payments;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.domain.OrderProcessor;
import pl.training.payments.ports.PaymentsService;
import pl.training.payments.ports.ShopService;

@EnableFeignClients
@Configuration
public class ShopConfiguration {

    @Bean
    public ShopService shopService(PaymentsService paymentsService) {
        return new OrderProcessor(paymentsService);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
