package pl.training.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var currentTime = LocalTime.now();
            if (currentTime.isAfter(config.startTime) && currentTime.isBefore(config.endTime)) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("startTime", "endTime");
    }

    public static class Config {

        private static final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern("H:m");

        private LocalTime startTime;
        private LocalTime endTime;

        public void setStartTime(String startTime) {
            this.startTime = LocalTime.parse(startTime, DATE_TIME_FORMATTER);
        }

        public void setEndTime(String endTime) {
            this.endTime = LocalTime.parse(endTime, DATE_TIME_FORMATTER);
        }

    }

}
