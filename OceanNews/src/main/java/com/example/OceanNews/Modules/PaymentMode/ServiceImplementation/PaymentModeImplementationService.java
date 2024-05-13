package com.example.OceanNews.Modules.PaymentMode.ServiceImplementation;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import com.example.OceanNews.Modules.PaymentMode.Repository.PaymentModeRepository;
import com.example.OceanNews.Modules.PaymentMode.Service.PaymentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentModeImplementationService implements PaymentModeService {
    @Autowired
    private PaymentModeRepository paymentModeRepository;
    @Override
    public void addPaymentMode(PaymentMode paymentMode) throws ELException {
        //checking if payment name exist
        Long pay= paymentModeRepository.findByName(paymentMode.getPaymentMode());
        if (paymentModeRepository.findByName(paymentMode.getPaymentMode())>=1){
             return;
        }
        paymentMode.setPaymentMode(paymentMode.getPaymentMode().toUpperCase());
        paymentMode.setAccountName(paymentMode.getAccountName().toUpperCase());
        paymentMode.setAccountNumber(paymentMode.getAccountNumber());
        paymentMode.setPaymentService(paymentMode.getPaymentService().toUpperCase());
        paymentModeRepository.save(paymentMode);
    }

    @Override
    public Iterable<PaymentMode> getAllPaymentMode() throws ELException {
        return paymentModeRepository.findAll();
    }

    @Override
    public String removePaymentMode(Long paymentModeID) throws ELException {
        //check if the id is existed
        if (!paymentModeRepository.existsById(paymentModeID)) {
            return "Payment id is not existed";
        }
        paymentModeRepository.deleteById(paymentModeID);
        return "Payment removed successfully";
    }

    @Override
    public String deletePatmentMode(Long paymentModeID) throws ELException {
        //check if the id is existed
        if (!paymentModeRepository.existsById(paymentModeID)) {
            return "Payment id is not existed";
        }
        //check if isDeleted is No
        PaymentMode paymentMode=paymentModeRepository.findById(paymentModeID).get();
        if (paymentMode.getIsDeleted().equals("Yes")){
            return "Payment id is already deleted";
        }
        //setting the isDeleted to Yes
        paymentMode.setIsDeleted("Yes");
        //setting the deletedAt to current time
        paymentMode.setDeletedAt( LocalDateTime.now());
        paymentModeRepository.save(paymentMode);
        return "Payment deleted successfully";
    }

    @Override
     public Long checkPaymentName(String name) throws ELException {
        return paymentModeRepository.findByName(name);
    }
}
