package com.example.OceanNews.Modules.PaymentMode.Service;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import org.springframework.stereotype.Service;

@Service
public interface PaymentModeService {
    void addPaymentMode(PaymentMode paymentMode) throws ELException;
    Iterable<PaymentMode> getAllPaymentMode()throws ELException;
    String removePaymentMode(Long paymentModeID)throws ELException;
    String deletePatmentMode(Long paymentModeID)throws ELException;
    Long checkPaymentName(String name)throws ELException;

}
