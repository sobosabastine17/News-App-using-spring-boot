package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.PaymentMode;
import com.example.OceanNews.Repo.PaymentModeRepository;
import com.example.OceanNews.Serivices.PaymentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentModeImplementationService implements PaymentModeService {
    @Autowired
    private PaymentModeRepository paymentModeRepository;
    @Override
    public void addPaymentMode(PaymentMode paymentMode) throws ELException {
        //checking if payment name exist
        PaymentMode pay= paymentModeRepository.findByName(paymentMode.getPaymentMode());
        //checking if name exist
        if (pay==null){
            ResponseEntity.badRequest().body("Payment Mode or Name Already in the system");
        }
        paymentMode.setPaymentMode(paymentMode.getPaymentMode());
        paymentMode.setAccountName(paymentMode.getAccountName());
        paymentMode.setAccountNumber(paymentMode.getAccountNumber());
        paymentModeRepository.save(paymentMode);
        ResponseEntity.ok("Payment added successfully");
    }

    @Override
    public String editPaymentMode(PaymentMode paymentMode) throws ELException {
        return null;
    }

    @Override
    public Iterable<PaymentMode> getAllPaymentMode() throws ELException {
        return null;
    }

    @Override
    public String removePaymentMode(Long paymentModeID) throws ELException {
        return null;
    }

    @Override
    public String deletePatmentMode(Long paymentModeID) throws ELException {
        return null;
    }
}
