package com.codeshuttle.uber.services;

import com.codeshuttle.uber.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
