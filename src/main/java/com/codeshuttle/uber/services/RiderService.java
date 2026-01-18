package com.codeshuttle.uber.services;

import com.codeshuttle.uber.dto.DriverDto;
import com.codeshuttle.uber.dto.RideDto;
import com.codeshuttle.uber.dto.RideRequestDto;
import com.codeshuttle.uber.dto.RiderDto;
import com.codeshuttle.uber.entities.Rider;
import com.codeshuttle.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto ride);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
