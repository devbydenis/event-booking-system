package com.sinaukoding.eventbookingsystem.model.enums;

import lombok.Getter;

@Getter
public enum Status {

    SUCCESS("Success"),
    PENDING("Pending"),
    FAILED("Failed");

    private final String label;

    Status(String label) {
        this.label = label;
    }

}