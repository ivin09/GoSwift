package com.codeshuttle.uber.services;

import com.codeshuttle.uber.entities.Payment;
import com.codeshuttle.uber.entities.Ride;
import com.codeshuttle.uber.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
