package pl.training.payments.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public class Tokens {

    private static final String TOKEN_TYPE = "Bearer ";

    public static Optional<String> getToken(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            return Optional.of(jwtAuthenticationToken)
                    .map(AbstractOAuth2TokenAuthenticationToken::getToken)
                    .map(AbstractOAuth2Token::getTokenValue);
        }
        return Optional.empty();
    }

    public static Optional<String> getAuthenticationHeaderValue(){
        return getToken().map(token -> TOKEN_TYPE + token);
    }

}
