package com.example.OceanNews.Modules.PaymentMode;

import com.example.OceanNews.Model.Model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PaymentMode extends Model {
    private String paymentMode;
    private String paymentService;
    private String accountNumber;
    private String accountName;
}
