package com.sinaukoding.eventbookingsystem.model.filter;

import java.time.LocalDateTime;

public record EventFilterRecord(
        String nama,
        LocalDateTime tanggalEvent,
        String lokasi,
        Integer kapasitas,
        Double harga
){}
