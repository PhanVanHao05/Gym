package com.pvh.gym_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptsDTO {
    private int userId;
    private int membershipTierId;
    private int paymentMethodId;
    private long paymentId;
    private Date receiptDate;
    private double amount;
}
