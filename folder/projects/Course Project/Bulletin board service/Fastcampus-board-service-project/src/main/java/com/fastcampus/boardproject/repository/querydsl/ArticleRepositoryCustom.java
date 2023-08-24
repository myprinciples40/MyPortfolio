package com.fastcampus.boardproject.repository.querydsl;

import com.fastcampus.boardproject.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface ArticleRepositoryCustom {

    /**
     * @deprecated Since I created a new hashtag domain, I don't need to use this code anymore.
     * @see HashtagRepositoryCustom#findAllHashtagNames()
     */
    @Deprecated
    List<String> findAllDistinctHashtags();
    Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);
}
