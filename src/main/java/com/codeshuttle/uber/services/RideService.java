package com.codeshuttle.uber.services;

import com.codeshuttle.uber.dto.RideRequestDto;
import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.Ride;
import com.codeshuttle.uber.entities.RideRequest;
import com.codeshuttle.uber.entities.Rider;
import com.codeshuttle.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
