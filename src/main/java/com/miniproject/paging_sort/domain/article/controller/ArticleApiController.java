package com.miniproject.paging_sort.domain.article.controller;

import com.miniproject.paging_sort.domain.article.ArticleSearchCondition;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import com.miniproject.paging_sort.domain.article.repository.ArticleRepository;
import com.miniproject.paging_sort.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public Page<ArticleDto> listWithPagingWithSearch(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){
        //검색 조건
        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setTitle(title);
        //페이징
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.searchPageComplex(condition,pageable);
    }
    /*
    @GetMapping("/api/articleList")
    public List<ArticleDto> articleList(){
    }

     */

    //단 건 조회
    @GetMapping("/api/articles/{id}")
    public ArticleDto showOne(@PathVariable Long id){
       return articleService.showOne(id);
    }


}

