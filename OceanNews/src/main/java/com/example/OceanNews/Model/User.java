package com.example.OceanNews.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    //@JsonIgnore
    private String password;
    private String email;
    private Long status=2L;
    private String avatar;
    private String fullname;
    private String address;
    private String phone;
    private String gender;
    @Enumerated(EnumType.STRING)
    private Payment_Mode paymentMode;
    private String payment_details;
    private String dob;
    private LocalDate created_at=LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Role roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(roles.name())
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
