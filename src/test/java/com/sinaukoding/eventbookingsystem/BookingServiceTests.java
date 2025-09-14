package com.sinaukoding.eventbookingsystem;

import com.sinaukoding.eventbookingsystem.entity.transaction.Booking;
import com.sinaukoding.eventbookingsystem.mapper.transaction.BookingMapper;
import com.sinaukoding.eventbookingsystem.model.enums.Status;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.transaction.BookingRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.transaction.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void testAddBooking_Success() {
        var request = new BookingRequestRecord(null, "008c7ebc-7564-43c5-90b1-cde1889a8a47", "f853baed-df67-4dff-937f-4d1275b38b90", Status.SUCCESS, 10);

        var bookingEntity = new Booking();
        when(bookingMapper.requestToEntity(request)).thenReturn(bookingEntity);

        // whrn
        bookingService.createBooking(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(bookingRepository, times(1)).save(bookingEntity);


    }

}
