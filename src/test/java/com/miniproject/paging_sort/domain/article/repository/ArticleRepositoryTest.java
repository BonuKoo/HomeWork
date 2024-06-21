package com.miniproject.paging_sort.domain.article.repository;

import com.miniproject.paging_sort.domain.article.ArticleSearchCondition;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository repository;
    @Autowired
    EntityManager entityManager;

    /*
    @Test //완료
        void CreateTest(){

        //Given
        Article article = new Article("제목","내용");

        //When
        Article one = repository.save(article);

        Optional<Article> idOneFromRepo = repository.findById(1L);

        //Then
        org.assertj.core.api.Assertions.assertThat(idOneFromRepo.equals(one));
    }
    @Test //더미데이터
      void Create_5000_Dummy_Data(){
        //Given
        for (int i = 1; i<=5000; i++){
            Article article = new Article();
            article.setTitle("제목" + i);
            article.setContent("내용" + i);
            repository.save(article);
        }
        //When
        List<Article> articleAll = repository.findAll();
        int size = articleAll.size();
        //Then
        Assertions.assertEquals(size,203);
    }
     */
    @Test
    void searchTest(){
        //Given
        /*
            조건 offset = 3, limit = 3
            검색 조건 : 제목
         */

        int offset = 0;
        int limit = 10;

        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setTitle("제목");
        Pageable pageable = PageRequest.of(offset,limit);
        //When
        Page<ArticleDto> resultPage = repository.searchPageComplex(condition, pageable);
        //Then
        assertThat(resultPage).isNotNull();
        assertThat(resultPage.getContent()).isNotEmpty();

        for (ArticleDto articleDto : resultPage.getContent()) {
            assertThat(articleDto.getTitle()).containsIgnoringCase("제목");
        }

    }
}