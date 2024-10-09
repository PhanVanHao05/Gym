package com.pvh.gym_management.controllers;

import com.pvh.gym_management.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private  PaymentService paymentService;


    @PostMapping("/create")
    public ResponseEntity<?> createPaymentUrl(@RequestBody Map<String, Object> payload) {
        try {
            String orderInfo = (String) payload.get("orderInfo");
            long amount = ((Number) payload.get("amount")).longValue();
            String paymentUrl = paymentService.createPaymentUrl(amount, orderInfo);
            return ResponseEntity.ok(paymentUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating payment URL");
        }
    }

    @GetMapping("/return")
    public ResponseEntity<?> handleVnpayReturn(@RequestParam Map<String, String> params) {
        String responseCode = params.get("vnp_ResponseCode");
        if ("00".equals(responseCode)) {
            Map<String, Object> paymentDetails = new HashMap<>();
            paymentDetails.put("paymentCode", params.get("vnp_TmnCode"));
            paymentDetails.put("bankCode", params.get("vnp_BankCode"));
            paymentDetails.put("transactionNo", params.get("vnp_TransactionNo"));
            paymentDetails.put("bankTransactionNo", params.get("vnp_BankTranNo"));
            paymentDetails.put("cardType", params.get("vnp_CardType"));
            paymentDetails.put("confirmAt", new Timestamp(System.currentTimeMillis()).toString());

            return ResponseEntity.ok(paymentDetails);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Payment failed", "responseCode", responseCode));
        }
    }

}
