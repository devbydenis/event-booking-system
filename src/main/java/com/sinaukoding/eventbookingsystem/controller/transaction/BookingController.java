package com.sinaukoding.eventbookingsystem.controller.transaction;

import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.transaction.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
public class BookingController {

    public final BookingService bookingService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public BaseResponse<?> createBookingController(@RequestBody BookingRequestRecord request) {
        bookingService.createBooking(request);
        return BaseResponse.ok("Berhasil membuat booking baru", null);
    }

    @PostMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> updateBookingController(@RequestBody BookingRequestRecord request) {
        bookingService.updateBooking(request);
        return BaseResponse.ok("Berhasil mengubah booking dengan id " + request.userId(), null);
    }

}
