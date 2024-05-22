package pl.training.payments.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
public class JwtTokenRestController {

    private final JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("tokens")
    public ResponseEntity<String> token(@RequestBody CredentialsDto credentialsDto) {
        return jwtAuthenticationService.authenticate(credentialsDto.getUsername(), credentialsDto.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(UNAUTHORIZED).build());
    }

}
