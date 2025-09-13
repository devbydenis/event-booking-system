package com.sinaukoding.eventbookingsystem.service.app;

import com.sinaukoding.eventbookingsystem.entity.managementuser.User;
import com.sinaukoding.eventbookingsystem.model.request.LoginRequestRecord;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;

public interface AuthService {

    SimpleMap register(RegisterRequestRecord request);
    SimpleMap login(LoginRequestRecord request);
    void logout(User userLoggedIn);

}
