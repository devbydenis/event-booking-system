package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    USER("User"),
    ADMIN("Admin");

    private final String label;

    Role(String label) { this.label = label; }

}
