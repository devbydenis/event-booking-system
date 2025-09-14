package com.sinaukoding.eventbookingsystem;

import com.sinaukoding.eventbookingsystem.entity.master.Event;
import com.sinaukoding.eventbookingsystem.mapper.master.EventMapper;
import com.sinaukoding.eventbookingsystem.model.request.EventRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.master.EventRepository;
import com.sinaukoding.eventbookingsystem.service.app.ValidatorService;
import com.sinaukoding.eventbookingsystem.service.master.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void testAddEvent_Success() {
        Set<String> listImage = new HashSet<>();
        listImage.add("path1");

        var request = new EventRequestRecord(null, "Sinau x ICON", "ga mudah tapi daging banget",
                null, "Jakarta Selatan", 34, 10.0, listImage);

        var eventEntity = new Event();
        when(eventMapper.requestToEntity(request)).thenReturn(eventEntity);

        // when
        eventService.createEvent(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(eventRepository, times(1)).save(eventEntity);
    }

}
