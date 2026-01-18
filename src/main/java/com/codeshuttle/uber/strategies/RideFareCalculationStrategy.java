package com.codeshuttle.uber.strategies;

import com.codeshuttle.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 100;

    double calculateFare(RideRequest rideRequest);
}
