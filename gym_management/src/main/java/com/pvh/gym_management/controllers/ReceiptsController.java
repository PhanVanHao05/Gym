package com.pvh.gym_management.controllers;

import com.pvh.gym_management.dtos.ReceiptsDTO;
import com.pvh.gym_management.mappers.ReceiptsDTOMapper;
import com.pvh.gym_management.pojo.Receipts;
import com.pvh.gym_management.services.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptsController {

    @Autowired
    private ReceiptsService receiptsService;

    @PostMapping("/create")
    public ResponseEntity<ReceiptsDTO> createReceipt(@RequestBody ReceiptsDTO receiptsDTO) {
        try {
            Receipts receipt = ReceiptsDTOMapper.toEntity(receiptsDTO);
            Receipts createdReceipt = receiptsService.createReceipt(receipt, receiptsDTO);

            ReceiptsDTO createdReceiptDTO = ReceiptsDTOMapper.toDTO(createdReceipt);
            return new ResponseEntity<>(createdReceiptDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
