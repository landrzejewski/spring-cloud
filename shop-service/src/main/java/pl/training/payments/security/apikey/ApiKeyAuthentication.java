package pl.training.payments.security.apikey;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import static java.util.Collections.emptyList;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    @Getter
    private final String apiKey;

    public ApiKeyAuthentication(String apiKey) {
        super(emptyList());
        this.apiKey = apiKey;
    }

    @Override
    public Object getCredentials() {
        return apiKey;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
