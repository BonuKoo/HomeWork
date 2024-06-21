package com.miniproject.paging_sort.domain.article.repository;

import com.miniproject.paging_sort.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long>, ArticleRepository4QueryDsl{
}
