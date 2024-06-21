package com.miniproject.paging_sort.domain.article.service;

import com.miniproject.paging_sort.domain.article.Article;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import com.miniproject.paging_sort.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    //단 건 조회
    public ArticleDto showOne(Long id){
        Article article = repository.findById(id).orElse(null);
        //예외 생략
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
    //전부 조회
    public List<ArticleDto> showAll(){
        List<Article> articles = repository.findAll();
        return articles.stream().map(
                article -> new ArticleDto(article.getId(), article.getTitle(), article.getContent())
        ).toList();
    }
}
