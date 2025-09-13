package com.sinaukoding.eventbookingsystem.service.app;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.request.RegisterRequestRecord;

public interface AuthService {

    SimpleMap register(RegisterRequestRecord request);


}
