package com.fastcampus.boardproject.service;

import com.fastcampus.boardproject.dto.ArticleCommentDto;
import com.fastcampus.boardproject.repository.ArticleCommentRepository;
import com.fastcampus.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(long articlId) {
        return List.of();
    }
}
