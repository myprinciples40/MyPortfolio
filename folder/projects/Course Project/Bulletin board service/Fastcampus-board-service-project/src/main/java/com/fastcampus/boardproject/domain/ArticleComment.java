package com.fastcampus.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article; //Post (ID)
    @Setter @Column(nullable = false, length = 500) private String content; //Body

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //Creation Date
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; //Constructor
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //Modification Date
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //who modifies

    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (!(o instanceof ArticleComment that)) return false;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
