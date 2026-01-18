package com.codeshuttle.uber.strategies.impl;

import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.RideRequest;
import com.codeshuttle.uber.repositories.DriverRepository;
import com.codeshuttle.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
