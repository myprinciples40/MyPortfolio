package com.fastcampus.boardproject.domain.constant;

import lombok.Getter;

public enum SearchType {
    TITLE("Title"),
    BODY("Body"),
    ID("User Id"),
    NICKNAME("Nickname"),
    HASHTAG("Hashtag");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
