package pl.training.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RequestLoggerGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestLoggerGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            Logger.getLogger(config.name)
                    .log(config.level, "New request for path: %s".formatted(exchange.getRequest().getPath()));
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("level", "name");
    }

    public static class Config {

        private Level level;
        private String name = RequestLoggerGatewayFilterFactory.class.getName();

        public void setLevel(String level) {
            this.level = Level.parse(level.toLowerCase());
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
