package com.example.OceanNews.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String role;
    private String status;
    private String avatar;
    private String fullname;
    private String address;
    private String phone;
    private String gender;
    private String dob;
    private String created_at;
    
    public User() {
        // Default constructor
    }
}
