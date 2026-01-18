package com.codeshuttle.uber.services.impl;

import com.codeshuttle.uber.dto.DriverDto;
import com.codeshuttle.uber.dto.SignUpDto;
import com.codeshuttle.uber.dto.UserDto;
import com.codeshuttle.uber.entities.Driver;
import com.codeshuttle.uber.entities.User;
import com.codeshuttle.uber.entities.enums.Role;
import com.codeshuttle.uber.exceptions.ResourceNotFoundException;
import com.codeshuttle.uber.exceptions.RuntimeConflictException;
import com.codeshuttle.uber.repositories.UserRepository;
import com.codeshuttle.uber.security.JWTService;
import com.codeshuttle.uber.services.AuthService;
import com.codeshuttle.uber.services.DriverService;
import com.codeshuttle.uber.services.RiderService;
import com.codeshuttle.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if (user != null) throw new RuntimeConflictException("User already exists with email: " + signUpDto.getEmail());

        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        //user relaTED ACTIVITIES
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (user.getRoles().contains(Role.DRIVER))
            throw new RuntimeConflictException("user with id " + userId + " already has role " + Role.DRIVER);

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver =  driverService.createNewDriver(createDriver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
                "with id: "+userId));

        return jwtService.generateAccessToken(user);
    }
}
