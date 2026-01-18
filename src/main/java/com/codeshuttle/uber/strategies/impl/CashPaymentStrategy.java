package com.codeshuttle.uber.strategies.impl;

import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.Payment;
import com.codeshuttle.uber.entities.enums.PaymentStatus;
import com.codeshuttle.uber.entities.enums.TransactionMethod;
import com.codeshuttle.uber.repositories.PaymentRepository;
import com.codeshuttle.uber.services.WalletService;
import com.codeshuttle.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
