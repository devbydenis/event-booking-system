package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.Status;
import jakarta.validation.constraints.NotBlank;

public record BookingRequestRecord(
        String id,
        @NotBlank(message = "userId tidak boleh kosong") String userId,
        @NotBlank(message = "eventId tidak boleh kosong") String eventId,
        Status status,
        Integer kuantitas
        ) {
}
