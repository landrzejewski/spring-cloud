package pl.training.payments.security.jwt;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class JwtAuthentication extends AbstractAuthenticationToken {

    @Getter
    private final String token;

    public JwtAuthentication(String token) {
        super(List.of());
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
