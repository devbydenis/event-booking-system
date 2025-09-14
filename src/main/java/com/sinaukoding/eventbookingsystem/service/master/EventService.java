package com.sinaukoding.eventbookingsystem.service.master;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    void createEvent(EventRequestRecord request);
    Page<SimpleMap> findAllEvent(EventFilterRecord filterRequest, Pageable pageable);
    SimpleMap findById(String id);
    void editEvent(EventRequestRecord request);
    void delete(String id);
}
