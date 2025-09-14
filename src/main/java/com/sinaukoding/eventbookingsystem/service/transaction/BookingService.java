package com.sinaukoding.eventbookingsystem.service.transaction;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    void createBooking(BookingRequestRecord request);
    void updateBooking(BookingRequestRecord request);
    void deleteBooking(String id);
    SimpleMap findByIdBooking(String id);
    Page<SimpleMap> findAllBooking(BookingFilterRecord filterRequest, Pageable pageable);
}
