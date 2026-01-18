package com.codeshuttle.uber.strategies;

import com.codeshuttle.uber.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);

}
