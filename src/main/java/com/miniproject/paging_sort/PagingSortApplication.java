package com.miniproject.paging_sort;

import com.miniproject.paging_sort.domain.article.Article;
import com.miniproject.paging_sort.domain.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PagingSortApplication implements CommandLineRunner {

	@Autowired
	public ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PagingSortApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		for (int i = 1; i<=100; i++){
			articleRepository.save(new Article(null,"title"+i,"content"+i));
		}
			 */
	}
}
