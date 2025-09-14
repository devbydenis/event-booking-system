package com.sinaukoding.eventbookingsystem.service.master.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.mapper.master.EventMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.master.EventService;
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
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ValidatorService validatorService;

    @Override
    public void createEvent(EventRequestRecord request){
        try {
            // validator mandatory
            validatorService.validator(request);

            // mapping ke entity lalu simpan ke db
            var event = eventMapper.requestToEntity(request);
            eventRepository.save(event);

        } catch (Exception e) {
            log.error("Gagal menambahkan data event: {}", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Page<SimpleMap> findAllEvent(EventFilterRecord filterRequest, Pageable pageable){
        CustomBuilder<Event> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotBlankLike("lokasi", filterRequest.nama(), builder);

        Page<Event> listAllEvent = eventRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listAllEvent.stream().map(event -> {
            SimpleMap data = new SimpleMap();
                    data.put("id", event.getId());
                    data.put("nama", event.getNama());
                    data.put("deskripsi", event.getDeskripsi());
                    data.put("tanggalEvent", event.getTanggalEvent());
                    data.put("lokasi", event.getLokasi());
                    data.put("kapasitas", event.getKapasitas());
                    data.put("harga", event.getHarga());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listAllEvent.getTotalElements());
    }

}
