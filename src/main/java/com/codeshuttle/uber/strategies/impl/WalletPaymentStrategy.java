package com.codeshuttle.uber.strategies.impl;

import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.Payment;
import com.codeshuttle.uber.entities.Rider;
import com.codeshuttle.uber.entities.enums.PaymentStatus;
import com.codeshuttle.uber.entities.enums.TransactionMethod;
import com.codeshuttle.uber.repositories.PaymentRepository;
import com.codeshuttle.uber.services.WalletService;
import com.codeshuttle.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),
                null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() - (1- PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(),
                TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
