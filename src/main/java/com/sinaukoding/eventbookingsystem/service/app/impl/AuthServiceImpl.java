package com.sinaukoding.eventbookingsystem.service.app.impl;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.mapper.app.RegisterUserMapper;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.enums.Role;
import com.sinaukoding.eventbookingsystem.model.request.LoginRequestRecord;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.service.app.AuthService;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RegisterUserMapper registerUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ValidatorService validatorService;

    @Override
    public SimpleMap register(RegisterRequestRecord request) {
        validatorService.validator(request);

        var currentUsername = userRepository.findByUsername(request.username().toLowerCase()).orElse(null);
        if (currentUsername != null) {
            throw new RuntimeException("Username sudah terdaftar");
        }

        var currentEmail = userRepository.findByEmail(request.email().trim().toLowerCase()).orElse(null);
        if(currentEmail != null){
            throw new RuntimeException("Email sudah digunakan");
        }

        var user = registerUserMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);

        SimpleMap registeredUser = new SimpleMap();
        registeredUser.put("nama", request.nama());
        registeredUser.put("username", request.username());
        registeredUser.put("email", request.email());

        return registeredUser;
    }

    @Override
    public SimpleMap login(LoginRequestRecord request) {
        validatorService.validator(request);
        var user = userRepository.findByUsername(request.username().toLowerCase()).orElseThrow(() -> new RuntimeException("Username atau password salah"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Username atau password salah");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        user.setToken(token);
        user.setExpiredTokenAt(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        SimpleMap result = new SimpleMap();
        result.put("token", token);
        return result;
    }

    @Override
    public void logout(User userLoggedIn) {
        userLoggedIn.setToken(null);
        userLoggedIn.setExpiredTokenAt(null);
        userRepository.save(userLoggedIn);
    }

}
