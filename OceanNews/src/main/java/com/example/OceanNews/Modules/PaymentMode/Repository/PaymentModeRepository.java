package com.example.OceanNews.Modules.PaymentMode.Repository;

import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode,Long> {

    //@Query("SELECT u FROM PaymentMode u WHERE u.paymentMode >= ?1")
    @Query("SELECT COUNT(e) FROM PaymentMode e WHERE e.paymentMode = :name")
    Long findByName(String name);
}
