package com.example.OceanNews.Modules.User;

import com.example.OceanNews.Model.Model;
import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import com.example.OceanNews.Modules.Roles.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@Entity
public class User extends Model {
    private String username;
    @NonNull
    private String writerName;
    //@JsonIgnore
    private String password;
    private String email;
    private String avatar;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String gender;
    @ManyToOne
    private PaymentMode paymentMode;
    private String dob;
    @ManyToOne
    private Roles roles;
    private boolean emailVerified=false;
    private Boolean tokenExpiry=false;
    private LocalDate lastLogin=LocalDate.now();
    private String token;

}
