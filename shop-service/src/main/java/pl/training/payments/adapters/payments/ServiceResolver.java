package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ServiceResolver {

    private final DiscoveryClient discoveryClient;
    private final Random random = new Random();

    public Optional<URI> get(String name) {
        var instances = discoveryClient.getInstances(name);
        if (instances.isEmpty()) {
            return Optional.empty();
        }
        var uri = instances.get(random.nextInt(instances.size()));
        return Optional.of(uri.getUri());
    }

}
