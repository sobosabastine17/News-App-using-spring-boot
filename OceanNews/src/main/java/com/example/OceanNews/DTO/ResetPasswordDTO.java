package com.example.OceanNews.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResetPasswordDTO {
    private String password;
    private String confirmPassword;
}
