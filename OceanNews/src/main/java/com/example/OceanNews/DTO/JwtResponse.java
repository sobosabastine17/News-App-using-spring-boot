package com.example.OceanNews.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
