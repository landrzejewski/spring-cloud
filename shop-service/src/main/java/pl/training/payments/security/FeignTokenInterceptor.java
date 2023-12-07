package pl.training.payments.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Tokens.createAuthorizationHederValue()
                .ifPresent(headerValue -> requestTemplate.header(AUTHORIZATION, headerValue));
    }

}
