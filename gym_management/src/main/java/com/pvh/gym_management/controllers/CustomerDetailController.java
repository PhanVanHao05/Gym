package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.CustomerInfoDTO;
import com.pvh.gym_management.pojo.CustomerDetail;
import com.pvh.gym_management.services.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer-details")
public class CustomerDetailController {
    @Autowired
    private CustomerDetailService customerDetailService;

    @PutMapping("/{userId}")
    public ResponseEntity<CustomerDetail> updateCustomerDetail(@PathVariable int userId, @RequestBody CustomerDetail customerDetailDetails) {
        return customerDetailService.updateCustomerDetail(userId, customerDetailDetails)
                .map(updatedCustomerDetail -> ResponseEntity.ok(updatedCustomerDetail))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<CustomerDetail> createCustomerDetail(
            @PathVariable int userId,
            @RequestBody CustomerDetail customerDetail) {
        CustomerDetail createdCustomerDetail = customerDetailService.createCustomerDetail(customerDetail, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDetail);
    }

    //Lấy thông tin khách hàng kèm số liệu cơ thể bằng id
    @GetMapping("/{userId}")
    public ResponseEntity<CustomerInfoDTO> getCustomerInfoById(@PathVariable int userId) {
        Optional<CustomerInfoDTO> customerInfoDTO = customerDetailService.getCustomerInfoById(userId);
        return customerInfoDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Lấy thông tin khách hàng kèm số liệu cơ thể bằng user_id
    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomerInfoDTO> getCustomerInfoByUserId(@PathVariable int userId) {
        Optional<CustomerInfoDTO> customerInfoDTO = customerDetailService.getCustomerInfoByUserId(userId);
        return customerInfoDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    //Cập nhật thông tin cơ thể
    @PutMapping("/update_body-stats/{userId}")
    public ResponseEntity<CustomerDetail> updateBodyStats(@PathVariable int userId,
                                                          @RequestParam double height,
                                                          @RequestParam double weight) {
        Optional<CustomerDetail> customerDetailOptional = customerDetailService.updateBodyStats(userId, height, weight);
        return customerDetailOptional
                .map(detail -> ResponseEntity.ok(detail))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
