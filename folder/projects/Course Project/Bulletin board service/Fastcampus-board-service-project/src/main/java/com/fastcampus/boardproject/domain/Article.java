package com.fastcampus.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content; //body

    @Setter private String hashTag;

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //Creation Date
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; //Constructor
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //Modification Date
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //who modifies

    protected Article() {}

    private Article(String title, String content, String hashTag) {
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }

    public static Article of(String title, String content, String hashTag) {
        return new Article(title, content, hashTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (!(o instanceof Article article)) return false; //Java 14 pattern matching from jdk 17
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id); //All non-persistent entities are viewed as different values.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}