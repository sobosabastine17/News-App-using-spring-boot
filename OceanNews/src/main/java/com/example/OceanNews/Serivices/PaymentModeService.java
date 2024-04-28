package com.example.OceanNews.Serivices;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.PaymentMode;
import org.springframework.stereotype.Service;

@Service
public interface PaymentModeService {
    void addPaymentMode(PaymentMode paymentMode) throws ELException;
    String editPaymentMode(PaymentMode paymentMode)throws ELException;
    Iterable<PaymentMode> getAllPaymentMode()throws ELException;
    String removePaymentMode(Long paymentModeID)throws ELException;
    String deletePatmentMode(Long paymentModeID)throws ELException;

}
