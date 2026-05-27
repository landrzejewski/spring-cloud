package pl.training.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class WorkTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<WorkTimeGatewayFilterFactory.Config> {

    public WorkTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->  {
           var currentTime = LocalDateTime.now();
           if (currentTime.getHour() >= config.start.getHour() && currentTime.getHour() <= config.end.getHour()) {
               return chain.filter(exchange);
           } else  {
               exchange.getResponse().setStatusCode(UNAUTHORIZED);
               return exchange.getResponse().setComplete();
           }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("start", "end");
    }

    public static class Config  {

        private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("HH:mm");

        private LocalTime start;
        private LocalTime end;

        public void setStart(String start) {
            this.start = LocalTime.parse(start, formatter);
        }

        public void setEnd(String end) {
            this.end = LocalTime.parse(end, formatter);
        }

    }


}
