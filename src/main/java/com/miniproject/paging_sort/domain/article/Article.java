package com.miniproject.paging_sort.domain.article;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder
@Entity @Table(name = "articles")
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public Article(String title,String content){
        this.title=title;
        this.content=content;
    }
}
