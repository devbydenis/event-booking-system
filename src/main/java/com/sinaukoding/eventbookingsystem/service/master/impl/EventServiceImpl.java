package com.sinaukoding.eventbookingsystem.service.master.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.builder.CustomSpecification;
import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.mapper.master.EventMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import com.sinaukoding.eventbookingsystem.service.master.EventService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Page<SimpleMap> findAllEvent(EventFilterRecord filterRequest, Pageable pageable){
        CustomBuilder<Event> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotBlankLike("lokasi", filterRequest.lokasi(), builder);
        FilterUtil.builderConditionNotNullEqual("tanggalEvent", filterRequest.tanggalEvent(), builder);

        if (filterRequest.harga() != null) {
            builder.with("harga", CustomSpecification.OPERATION_EQUAL, filterRequest.harga());
        }

        Page<Event> listAllEvent = eventRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listAllEvent.stream().map(event -> {
            SimpleMap data = SimpleMap.createMap()
                    .add("id", event.getId())
                    .add("nama", event.getNama())
                    .add("deskripsi", event.getDeskripsi())
                    .add("tanggalEvent", event.getTanggalEvent())
                    .add("lokasi", event.getLokasi())
                    .add("kapasitas", event.getKapasitas())
                    .add("harga", event.getHarga());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listAllEvent.getTotalElements());
    }

}
