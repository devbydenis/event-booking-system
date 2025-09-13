package com.sinaukoding.eventbookingsystem.mapper.app;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapper {

    public User requestToEntity(RegisterRequestRecord request) {
        return User.builder()
                .nama(request.nama().toLowerCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .password(request.password().trim())
                .build();
    }

}
