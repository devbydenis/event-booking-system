package com.sinaukoding.eventbookingsystem.service.transaction.impl;

import com.sinaukoding.eventbookingsystem.mapper.transaction.BookingMapper;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.transaction.BookingRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.transaction.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final ValidatorService validatorService;

    @Override
    public void createBooking(BookingRequestRecord request) {
        try {
            validatorService.validator(request);

            var booking = bookingMapper.requestToEntity(request);
            bookingRepository.save(booking);
        } catch (Exception e){
            log.error("Gagal menambahkan booking: {}", e.getMessage());
        }
    }

    @Override
    public  void updateBooking(BookingRequestRecord request){
        try{
            validatorService.validator(request);

            var bookingExisting = bookingRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Booking tidak ditemukan"));
            var booking = bookingMapper.requestToEntity(request);
            booking.setId(bookingExisting.getId());
            bookingRepository.save(booking);
        } catch (Exception e) {
            log.error("Gagal mengubah data booking: {}", e.getMessage());
        }
    }


}
