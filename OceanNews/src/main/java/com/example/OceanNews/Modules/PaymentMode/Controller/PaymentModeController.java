package com.example.OceanNews.Modules.PaymentMode.Controller;

import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import com.example.OceanNews.Modules.PaymentMode.Service.PaymentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1")
public class PaymentModeController {
    @Autowired
    private PaymentModeService paymentModeService;
    @PostMapping("/paymentMode/add")
    ResponseEntity<String> addPaymentMode(@RequestBody PaymentMode paymentMode){
        //checking if payment name exist
        if (paymentModeService.checkPaymentName(paymentMode.getPaymentMode())>=1){
          return  ResponseEntity.badRequest().body("Payment name is already existed");
        }
         paymentModeService.addPaymentMode(paymentMode);
         return ResponseEntity.ok("Payment added successfully");
    }
    @GetMapping("/paymentMode/getAll")
    ResponseEntity<Iterable<PaymentMode>> getAllPaymentMode(){
        return ResponseEntity.ok(paymentModeService.getAllPaymentMode());
    }
    @DeleteMapping("/paymentMode/remove/{paymentModeID}")
    ResponseEntity<String> removePaymentMode(@PathVariable Long paymentModeID){
        //check if delete is successful
        if (paymentModeService.removePaymentMode(paymentModeID).equals("Payment id is not existed")){
            return ResponseEntity.badRequest().body("Payment id: "+paymentModeID+" is not existed");
        }
        return ResponseEntity.ok("Payment removed successfully");
    }
    @PatchMapping("/paymentMode/delete/{paymentModeID}")
    ResponseEntity<String> deletePaymentMode(@PathVariable Long paymentModeID){
        //check if delete is successful
        if (paymentModeService.deletePatmentMode(paymentModeID).equals("Payment id is not existed")){
            return ResponseEntity.badRequest().body("Payment id: "+paymentModeID+" is not existed");
        }
        if (paymentModeService.deletePatmentMode(paymentModeID).equals("Payment id is already deleted")){
            return ResponseEntity.badRequest().body("Payment id: "+paymentModeID+" is already deleted");
        }
        return ResponseEntity.ok("Payment deleted successfully");
    }
}
