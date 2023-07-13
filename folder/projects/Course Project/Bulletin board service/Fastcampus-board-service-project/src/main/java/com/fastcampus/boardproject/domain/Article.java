package com.fastcampus.boardproject.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content; //body
    private String hashTag;

    private LocalDateTime createdAt; //Creation Date
    private String createdBy; //Constructor
    private LocalDateTime modifiedAt; //Modification Date
    private String modifiedBy; //who modifies
}
