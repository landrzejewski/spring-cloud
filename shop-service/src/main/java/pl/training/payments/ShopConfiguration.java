package pl.training.payments;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.payments.domain.OrderProcessor;
import pl.training.payments.ports.ShopService;

@Log
@Configuration
public class ShopConfiguration {

    public ShopConfiguration(@Value("${discount}") long discount) {
        log.info("Current discount: " + discount);
    }

    @Bean
    public ShopService shopService() {
        return new OrderProcessor();
    }

}
