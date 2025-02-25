package pl.training.payments;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
@Log
@Configuration
public class ShopConfiguration {

    @Bean
    public ShopService shopService(PaymentService paymentService, ConstantDiscountCalculator discountCalculator) {
        return new OrderProcessor(paymentService, discountCalculator);
    }

    @RefreshScope
    @Bean
    public ConstantDiscountCalculator discountCalculator(@Value("${discount}") long discount) {
        return new ConstantDiscountCalculator(discount);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /*@Bean
    public HttpTracing create(Tracing tracing) {
        return HttpTracing.newBuilder(tracing)
                .build();
    }*/

    /*@LoadBalanced
    @Bean
    public RestTemplate restTemplate(*//*HttpTracing httpTracing,*//*RestTemplateBuilder restTemplateBuilder) {
     *//*return new RestTemplateBuilder()
                .additionalInterceptors(TracingClientHttpRequestInterceptor.create(httpTracing))
                .build();*//*
        return restTemplateBuilder.build();
    }*/

   /* @Bean
    public Consumer<PaymentEventDto> paymentEventsConsumer() {
        return event -> log.info("Payment status updated (id: %s)".formatted(event.getPaymentId()));
    }*/

}
