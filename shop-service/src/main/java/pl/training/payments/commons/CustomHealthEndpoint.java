package pl.training.payments.commons;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Endpoint(id = "custom-health")
@Component
public class CustomHealthEndpoint {

    @ReadOperation
    public String get() {
        return "Status ok (" + Instant.now() + ")";
    }

}
