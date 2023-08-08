package com.fastcampus.boardproject.controller;

import com.fastcampus.boardproject.dto.UserAccountDto;
import com.fastcampus.boardproject.dto.request.ArticleCommentRequest;
import com.fastcampus.boardproject.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;

    @PostMapping ("/new")
    public String postNewArticle(ArticleCommentRequest articleCommentRequest) {
        // TODO: I have to enter the credentials.
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "jin", "pw", "jin@gmail.com", null, null
        )));

        return "redirect:/articles" + articleCommentRequest.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
}
