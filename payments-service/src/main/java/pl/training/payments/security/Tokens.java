package pl.training.payments.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public class Tokens {

    private static final String TOKEN_PREFIX = "bearer ";

    public static Optional<String> getValue() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken authenticationToken) {
            return Optional.of(authenticationToken)
                    .map(JwtAuthenticationToken::getToken)
                    .map(AbstractOAuth2Token::getTokenValue);
        }
        return Optional.empty();
    }

    public static Optional<String> createAuthorizationHederValue() {
        return getValue().map(token -> TOKEN_PREFIX + token);
    }

}
