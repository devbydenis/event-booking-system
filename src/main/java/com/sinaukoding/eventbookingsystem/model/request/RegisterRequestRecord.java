package com.sinaukoding.eventbookingsystem.model.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestRecord(
        @NotBlank(message = "Nama tidak boleh kosong") String nama,
        @NotBlank(message = "Nama tidak boleh kosong") String email,
        @NotBlank(message = "Nama tidak boleh kosong") String username,
        @NotBlank(message = "Nama tidak boleh kosong") String password
) {
}
