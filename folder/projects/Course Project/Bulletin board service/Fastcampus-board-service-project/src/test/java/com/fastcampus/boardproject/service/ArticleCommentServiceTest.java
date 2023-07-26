package com.fastcampus.boardproject.service;

import com.fastcampus.boardproject.domain.Article;
import com.fastcampus.boardproject.domain.ArticleComment;
import com.fastcampus.boardproject.dto.ArticleCommentDto;
import com.fastcampus.boardproject.repository.ArticleCommentRepository;
import com.fastcampus.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("Business logic - comment")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("When queried by post id, returns a list of corresponding comments.")
    @Test
    void givenArticleIdInfo_whenSearchesArticleComments_thenReturnsArticleComments() {
        // Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "#java")));

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComments(articleId);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("When queried by post id, saves corresponding comments.")
    @Test
    void givenArticleIdInfo_whenSearchesArticleComments_thenSavesArticleComments() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComments(1L);

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}