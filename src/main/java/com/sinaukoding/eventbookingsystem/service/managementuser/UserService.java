package com.sinaukoding.eventbookingsystem.service.managementuser;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void addUser(UserRequestRecord request);
    void editUser(UserRequestRecord request);
    void deleteUser(String id);
    Page<SimpleMap> findAllUser(UserFilterRecord filterRequest, Pageable pageable);
}
