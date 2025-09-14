package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record EventRequestRecord(
        String id,
        @NotBlank(message = "Nama tidak boleh kosong") String nama,
        @NotBlank(message = "Deskripsi tidak boleh kosong") String deskripsi,
        @NotNull(message = "Tanggal tidak boleh kosong") LocalDateTime tanggalEvent,
        @NotBlank(message = "Lokasi tidak boleh kosong") String lokasi,
        @NotNull(message = "Kapasitas tidak boleh kosong") Integer kapasitas,
        @NotNull(message = "Harga tidak boleh kosong") Double harga,
        @NotEmpty(message = "Gambar tidak boleh kosong") Set<String> listImage
) {
}
