package com.giveus.funding.global.common.enums;

import lombok.Getter;

@Getter
public enum Folder {
    FUNDING("funding"),
    REVIEW("review");
    private final String name;

    Folder(String name) {
        this.name = name;
    }
}
