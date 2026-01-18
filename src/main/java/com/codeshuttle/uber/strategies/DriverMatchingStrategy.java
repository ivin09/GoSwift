package com.codeshuttle.uber.strategies;

import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
