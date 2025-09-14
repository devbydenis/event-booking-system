package com.sinaukoding.eventbookingsystem.mapper.master;

import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.entity.master.EventImage;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventMapper {

    public Event requestToEntity(EventRequestRecord request){
        Event event =  Event.builder()
                .nama(request.nama().trim().toLowerCase())
                .deskripsi(request.deskripsi().trim().toLowerCase())
                .tanggalEvent(request.tanggalEvent())
                .lokasi(request.lokasi().trim())
                .kapasitas(request.kapasitas())
                .harga(request.harga())
                .build();

        event.setListImage(request.listImage().stream()
                .map(path -> EventImage.builder()
                        .path(path)
                        .event(event)
                        .build())
                .collect(Collectors.toSet()));

        return event;
    }

}
