package com.codeshuttle.uber.services;

import com.codeshuttle.uber.dto.DriverDto;
import com.codeshuttle.uber.dto.SignUpDto;
import com.codeshuttle.uber.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signUp(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
