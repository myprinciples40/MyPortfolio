package com.fastcampus.boardproject.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@ToString
@Table(indexes = )
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