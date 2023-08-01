package com.fastcampus.boardproject.controller;

import com.fastcampus.boardproject.config.SecurityConfig;
import com.fastcampus.boardproject.dto.ArticleWithCommentsDto;
import com.fastcampus.boardproject.dto.UserAccountDto;
import com.fastcampus.boardproject.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/***
 * /articles
 * /articles/{article-id}
 * /articles/search
 * /articles/search-hashtag
 */

@DisplayName("View Controller - Post")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;

    @MockBean private ArticleService articleService;

    ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] Post List (Bulletin Board) Page - Normal Invocation")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        // Given
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        // When & Then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }

    @DisplayName("[view][GET] Post Detail page - Normal Invocation")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        // Given
        Long articleId = 1L;
        given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());

        // When & Then
        mvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));
        then(articleService).should().getArticle(articleId);
    }

    @Disabled("Implementing")
    @DisplayName("[view][GET] Post search page - Normal Invocation")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search"));
    }

    @Disabled("Implementing")
    @DisplayName("[view][GET] Post hashtag search page - Normal Invocation")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search-hashtag"));
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "jin",
                LocalDateTime.now(),
                "jin"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(1L,
                "jin",
                "pw",
                "Jin@mail.com",
                "Jin",
                "memo",
                LocalDateTime.now(),
                "jin",
                LocalDateTime.now(),
                "jin"
        );
    }
}
