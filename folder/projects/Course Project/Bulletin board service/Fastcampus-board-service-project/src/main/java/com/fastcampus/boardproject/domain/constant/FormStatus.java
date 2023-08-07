package com.fastcampus.boardproject.domain.constant;

import lombok.Getter;

public enum FormStatus {
    CREATE("Save", false),
    UPDATE("Modify", true);

    @Getter
    private final String description;
    @Getter private final Boolean update;

    FormStatus(String description, Boolean update) {
        this.description = description;
        this.update = update;
    }
}
