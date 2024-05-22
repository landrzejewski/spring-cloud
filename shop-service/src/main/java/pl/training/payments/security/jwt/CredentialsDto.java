package pl.training.payments.security.jwt;

import lombok.Data;

@Data
public class CredentialsDto {

    private String username;
    private String password;

}
