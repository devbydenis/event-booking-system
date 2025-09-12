package com.sinaukoding.eventbookingsystem.service.managementuser;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<SimpleMap> findAllUser(UserFilterRecord filterRequest, Pageable pageable);
}
