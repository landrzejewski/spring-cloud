package pl.training.payments.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserRestController {

    @GetMapping("users/me")
    public UserEntity getUser(Authentication authentication, Principal principal) {
        var userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) userAuthentication.getPrincipal();
    }

}
