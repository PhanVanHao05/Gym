package com.pvh.gym_management.services;

import com.pvh.gym_management.dtos.ReceiptsDTO;
import com.pvh.gym_management.pojo.Receipts;

public interface ReceiptsService {
    Receipts createReceipt(Receipts receipt, ReceiptsDTO receiptsDTO);
}
