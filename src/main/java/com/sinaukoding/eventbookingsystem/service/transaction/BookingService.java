package com.sinaukoding.eventbookingsystem.service.transaction;

import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;

public interface BookingService {
    void createBooking(BookingRequestRecord request);
}
