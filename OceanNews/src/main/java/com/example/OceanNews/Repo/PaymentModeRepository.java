package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode,Long> {

    @Query("select u from PaymentMode u where u.paymentMode = ?1")
    PaymentMode findByName(String name);
}
