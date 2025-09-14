package com.sinaukoding.eventbookingsystem.controller.transaction;

import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.transaction.BookingService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("find-all")
    @Parameters({
            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
    })
    public BaseResponse<?> findAllEventController(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
            @RequestBody BookingFilterRecord filterRequest
    ) {
        return BaseResponse.ok("success", bookingService.findAllBooking(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findByIdBookingController(@PathVariable String id){
        return BaseResponse.ok("Berhasil memuat booking berdasarkan id", bookingService.findByIdBooking(id));
    }

    @PostMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> updateBookingController(@RequestBody BookingRequestRecord request) {
        bookingService.updateBooking(request);
        return BaseResponse.ok("Berhasil mengubah booking dengan id " + request.userId(), null);
    }

    @PostMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    public  BaseResponse<?> deleteBookingController(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return BaseResponse.ok("berhasil menghapus booking dengan id " + id, null);
    }

}
