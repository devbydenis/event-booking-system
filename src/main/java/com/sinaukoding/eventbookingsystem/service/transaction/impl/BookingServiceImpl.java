package com.sinaukoding.eventbookingsystem.service.transaction.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.entity.transaction.Booking;
import com.sinaukoding.eventbookingsystem.mapper.transaction.BookingMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.managementuser.UserRepository;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import com.sinaukoding.eventbookingsystem.repository.transaction.BookingRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.transaction.BookingService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
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
    public void updateBooking(BookingRequestRecord request){
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

    @Override
    public Page<SimpleMap> findAllBooking(BookingFilterRecord filterRequest, Pageable pageable){
        CustomBuilder<Booking> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("userId", filterRequest.userId(), builder);
        FilterUtil.builderConditionNotBlankLike("eventId", filterRequest.eventId(), builder);

        Page<Booking> listAllEvent = bookingRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listAllEvent.stream().map(event -> {
            SimpleMap data = new SimpleMap();
            data.put("id", event.getId());
            data.put("userId", event.getUserId());
            data.put("eventId", event.getEventId());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listAllEvent.getTotalElements());
    }

    @Override
    public SimpleMap findByIdBooking(String id){
        var booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking tidak ditemukan"));
        var user = userRepository.findById(booking.getUserId()).orElseThrow(() -> new RuntimeException("Tidak bisa menemukan user berdasarkan id booking"));
        var event = eventRepository.findById(booking.getEventId()).orElseThrow(() -> new RuntimeException("Tidak bisa menemukan event berdasarkan id booking"));

        SimpleMap data = new SimpleMap();
        data.put("bookingId", id);
        data.put("namaUser", user.getNama());
        data.put("namaEvent", event.getNama());
        data.put("tanggal", event.getTanggalEvent());
        data.put("harga", event.getHarga());
        data.put("kapasitas", event.getKapasitas());
        data.put("lokasi", event.getLokasi());
        data.put("kuantitas", booking.getKuantitas());
        data.put("statusBooking", booking.getStatus());

        return data;
    }

    @Override
    public void deleteBooking(String id) {
        var bookingExisting = bookingRepository.existsById(id);
        if (!bookingExisting) {
            throw new RuntimeException("Booking dengan id "+ id +" tidak tersedia");
        }

        bookingRepository.deleteById(id);
    }


}
