package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.Status;

public record BookingRequestRecord(
        String id,
        String userId,
        String eventId,
        Status status,
        Integer kuantitas
        ) {
}
