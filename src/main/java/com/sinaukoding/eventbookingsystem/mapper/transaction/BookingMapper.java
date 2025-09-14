package com.sinaukoding.eventbookingsystem.mapper.transaction;

import com.sinaukoding.eventbookingsystem.entity.transaction.Booking;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking requestToEntity(BookingRequestRecord request) {
        return Booking.builder()
                .userId(request.userId())
                .eventId(request.eventId())
                .kuantitas(request.kuantitas())
                .status(request.status())
                .build();

//        return booking;
    }

}
