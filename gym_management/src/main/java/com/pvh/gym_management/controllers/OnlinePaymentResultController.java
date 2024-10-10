package com.pvh.gym_management.controllers;

import com.pvh.gym_management.pojo.OnlinePaymentResult;
import com.pvh.gym_management.services.OnlinePaymentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/online-payments")
public class OnlinePaymentResultController {

    @Autowired
    private OnlinePaymentResultService onlinePaymentResultService;

    @PostMapping("/create")
    public ResponseEntity<OnlinePaymentResult> createOnlinePayment(@RequestBody OnlinePaymentResult onlinePaymentResult) {
        OnlinePaymentResult createdPayment = onlinePaymentResultService.createOnlinePaymentResult(onlinePaymentResult);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

}
