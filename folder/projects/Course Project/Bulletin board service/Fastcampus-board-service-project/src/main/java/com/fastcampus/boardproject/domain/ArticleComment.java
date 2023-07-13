package com.fastcampus.boardproject.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; //Post (ID)
    private String content; //Body

    private LocalDateTime createdAt; //Creation Date
    private String createdBy; //Constructor
    private LocalDateTime modifiedAt; //Modification Date
    private String modifiedBy; //who modifies
}
