package com.miniproject.paging_sort.domain.article.repository;

import com.miniproject.paging_sort.domain.article.ArticleSearchCondition;
import com.miniproject.paging_sort.domain.article.QArticle;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import com.miniproject.paging_sort.domain.article.dto.QArticleDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

public class ArticleRepository4QueryDslImpl implements ArticleRepository4QueryDsl{

    private final JPAQueryFactory queryFactory;

    QArticle article = new QArticle(QArticle.article);

    public ArticleRepository4QueryDslImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
    //제목 기준 검색조건
    @Override
    public List<ArticleDto> search(ArticleSearchCondition condition) {
        return queryFactory
                .select(new QArticleDto(
                        article.id,
                        article.title,
                        article.content
                ))
                .from(article)
                .where(titleEq(condition.getTitle()))
                .fetch();
    }

    @Override
    public Page<ArticleDto> searchPageComplex(ArticleSearchCondition condition, Pageable pageable) {

        JPAQuery<ArticleDto> query = queryFactory
                .select(new QArticleDto(
                        article.id,
                        article.title,
                        article.content
                ))
                .from(article)
                .where(titleEq(condition.getTitle())
                );
        //JPQL 쿼리 우선 만듬

        //정렬 - 오류뜨면 여기서 찾아라.
        if (pageable.getSort().isSorted()){
            for (Sort.Order order : pageable.getSort()){
                PathBuilder<Object> pathBuilder = new PathBuilder<>(ArticleDto.class,"articleDto");
                query.orderBy(new OrderSpecifier(order.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC,
                        pathBuilder.get(order.getProperty())));
            }
        }
        //위에 만든 거에다가 Paging
        List<ArticleDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //countQuery
        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(titleEq(condition.getTitle())
                );

        //countQuery 필요 X한 경우
        //return PageableExecutionUtils.getPage(content,pageable,);
        //countQuery 필요 O한 경우
        return PageableExecutionUtils.getPage(content,pageable,countQuery::fetchOne);
    }

    private BooleanExpression titleEq(String title){
        return StringUtils.hasText(title) ? null : article.title.eq(title);
    }

}
