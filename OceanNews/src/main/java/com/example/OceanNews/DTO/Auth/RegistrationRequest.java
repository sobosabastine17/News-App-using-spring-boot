package com.example.OceanNews.DTO.Auth;

import com.example.OceanNews.Model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private String address;
    private String phone;
    private String gender;
    private String dob;
    private Long status;
    private String payment_details;
    private final LocalDate created_at=LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Role role;
}
