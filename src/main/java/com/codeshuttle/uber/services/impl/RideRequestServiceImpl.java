package com.codeshuttle.uber.services.impl;

import com.codeshuttle.uber.entities.RideRequest;
import com.codeshuttle.uber.exceptions.ResourceNotFoundException;
import com.codeshuttle.uber.repositories.RideRequestRepository;
import com.codeshuttle.uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository
                .findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride Request Not Found with ID: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Ride Request Not Found with ID: " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
