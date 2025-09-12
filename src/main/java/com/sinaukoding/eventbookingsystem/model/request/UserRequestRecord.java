package com.sinaukoding.eventbookingsystem.model.request;

import com.sinaukoding.eventbookingsystem.model.enums.Role;

public record UserRequestRecord(String id,
                                String nama,
                                String username,
                                String email,
                                String password,
                                Role role) {
}
