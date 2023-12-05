package com.example.OceanNews.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String token;
}
