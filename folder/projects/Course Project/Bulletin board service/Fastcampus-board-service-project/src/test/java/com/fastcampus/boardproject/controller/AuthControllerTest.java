package com.fastcampus.boardproject.controller;

import com.fastcampus.boardproject.config.TestSecurityConfig;
import com.fastcampus.boardproject.service.ArticleService;
import com.fastcampus.boardproject.service.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View Controller - Authentication")
@Import(TestSecurityConfig.class)
@WebMvcTest(Void.class)
class AuthControllerTest {
    private final MockMvc mvc;

    @MockBean private ArticleService articleService;
    @MockBean private PaginationService paginationService;

    AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    // Testable by Spring security
    @DisplayName("[view][GET] Login Page - Normal Invocation")
    @Test
    void givenNothing_whenTryingToLoggingIn_thenReturnsLogInView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
        then(articleService).shouldHaveNoInteractions();
        then(paginationService).shouldHaveNoInteractions();
    }
}
