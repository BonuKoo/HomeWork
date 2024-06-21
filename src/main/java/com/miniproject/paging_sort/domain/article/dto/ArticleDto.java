package com.miniproject.paging_sort.domain.article.dto;

import com.miniproject.paging_sort.domain.article.Article;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ArticleDto {
    Long id;
    String title;
    String content;

    @QueryProjection
    public ArticleDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public Article toEntity(){
        return new Article(id,title,content);
    }


}
