package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.pojo.OnlinePaymentResult;
import com.pvh.gym_management.repositories.OnlinePaymentResultRepository;
import com.pvh.gym_management.services.OnlinePaymentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlinePaymentResultServiceImpl implements OnlinePaymentResultService {
    @Autowired
    private OnlinePaymentResultRepository onlinePaymentResultRepository;

    @Override
    public OnlinePaymentResult createOnlinePaymentResult(OnlinePaymentResult onlinePaymentResult) {
        return onlinePaymentResultRepository.save(onlinePaymentResult);
    }
}
