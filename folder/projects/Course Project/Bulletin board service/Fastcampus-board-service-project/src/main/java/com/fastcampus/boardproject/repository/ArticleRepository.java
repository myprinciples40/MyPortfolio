package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Add and annotate rest in application.yaml
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
