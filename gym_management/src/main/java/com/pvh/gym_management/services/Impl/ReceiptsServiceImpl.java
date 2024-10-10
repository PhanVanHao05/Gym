package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.ReceiptsDTO;
import com.pvh.gym_management.pojo.*;
import com.pvh.gym_management.repositories.*;
import com.pvh.gym_management.services.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReceiptsServiceImpl implements ReceiptsService {

    @Autowired
    private ReceiptsRepository receiptsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipTiersRepository membershipTiersRepository;

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;

    @Autowired
    private OnlinePaymentResultRepository paymentResultRepository;

    @Override
    public Receipts createReceipt(Receipts receipt, ReceiptsDTO receiptsDTO) {
        Optional<User> user = userRepository.findById(receiptsDTO.getUserId());
        Optional<MembershipTiers> membershipTier = membershipTiersRepository.findById(receiptsDTO.getMembershipTierId());
        Optional<PaymentMethods> paymentMethod = paymentMethodsRepository.findById(receiptsDTO.getPaymentMethodId());

        if (user.isPresent() && membershipTier.isPresent() && paymentMethod.isPresent()) {
            receipt.setUser(user.get());
            receipt.setMembershipTier(membershipTier.get());
            receipt.setPaymentMethod(paymentMethod.get());

            receipt.setReceiptDate(new Date());

            if (receiptsDTO.getPaymentId() != 0L) {
                Optional<OnlinePaymentResult> payment = paymentResultRepository.findById(receiptsDTO.getPaymentId());
                payment.ifPresent(receipt::setPayment);
            }

            return receiptsRepository.save(receipt);
        } else {
            throw new IllegalArgumentException("Invalid ID provided");
        }
    }

}
