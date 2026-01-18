package com.codeshuttle.uber.services;

import com.codeshuttle.uber.dto.DriverDto;
import com.codeshuttle.uber.dto.RiderDto;
import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.Ride;
import com.codeshuttle.uber.entities.Rider;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
