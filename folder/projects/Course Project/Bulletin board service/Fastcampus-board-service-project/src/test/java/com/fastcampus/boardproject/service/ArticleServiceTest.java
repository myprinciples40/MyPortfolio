package com.fastcampus.boardproject.service;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.type.SearchType;
import com.fastcampus.boardproject.dto.ArticleDto;
import com.fastcampus.boardproject.dto.ArticleUpdateDto;
import com.fastcampus.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("Business logic - post")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("When you search for posts, it returns a list of posts.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // title, body, Id, nickname, hashtag
        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("When you look up a post, it returns a post.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        // Given

        // When
        ArticleDto articles = sut.searchArticle(1L); // title, body, Id, nickname, hashtag
        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("After you enter the post information, create the post.")
    @Test
    void givenArticleInfo_whenSavesArticle_thenChecksSavingArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Jin", "title", "content", "hashtag"));

        // Then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("After you enter the Id and revision information, modify the post.")
    @Test
    void givenArticleIdAndModifiedInfo_whenAppliesToArticle_thenUpdatesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        // Then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("After you enter the Id, delete the post.")
    @Test
    void givenArticleIdInfo_whenAppliesToArticle_thenDeletesArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));
    }
}