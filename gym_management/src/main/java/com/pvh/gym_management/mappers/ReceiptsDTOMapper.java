package com.pvh.gym_management.mappers;

import com.pvh.gym_management.dtos.ReceiptsDTO;
import com.pvh.gym_management.pojo.Receipts;

public class ReceiptsDTOMapper {

    public static ReceiptsDTO toDTO(Receipts receipts) {
        if (receipts == null) {
            return null;
        }

        ReceiptsDTO dto = new ReceiptsDTO();
        dto.setUserId(receipts.getUser().getId());
        dto.setMembershipTierId(receipts.getMembershipTier().getId());
        dto.setPaymentMethodId(receipts.getPaymentMethod().getId());
        dto.setPaymentId(receipts.getPayment().getPaymentId());
        dto.setReceiptDate(receipts.getReceiptDate());
        dto.setAmount(receipts.getAmount());

        return dto;
    }

    public static Receipts toEntity(ReceiptsDTO dto) {
        if (dto == null) {
            return null;
        }

        Receipts receipts = new Receipts();
        receipts.setReceiptDate(dto.getReceiptDate());
        receipts.setAmount(dto.getAmount());

        return receipts;
    }
}
