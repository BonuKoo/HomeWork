package com.miniproject.paging_sort.domain.article.repository;

import com.miniproject.paging_sort.domain.article.ArticleSearchCondition;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepository4QueryDsl {

    List<ArticleDto> search(ArticleSearchCondition condition);
    //TotalPage 분리
    Page<ArticleDto> searchPageComplex(ArticleSearchCondition condition, Pageable pageable);
}
