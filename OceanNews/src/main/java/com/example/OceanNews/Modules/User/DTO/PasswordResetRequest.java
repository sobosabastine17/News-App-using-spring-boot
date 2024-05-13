package com.example.OceanNews.Modules.User.DTO;

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
