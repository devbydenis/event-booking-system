package com.sinaukoding.eventbookingsystem.mapper.managementuser;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
                .nama(request.nama().toLowerCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .role(request.role())
                .build();
    }

}
