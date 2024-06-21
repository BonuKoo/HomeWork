package com.miniproject.paging_sort.domain.article.controller;

import com.miniproject.paging_sort.domain.article.Article;
import com.miniproject.paging_sort.domain.article.dto.ArticleDto;
import com.miniproject.paging_sort.domain.article.repository.ArticleRepository;
import com.miniproject.paging_sort.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @GetMapping("/articles/{id}")
    public String showOne(@PathVariable Long id, Model model){
        ArticleDto article = articleService.showOne(id);
        model.addAttribute("article",article);
        return "article/viewArticle";
    }

    @GetMapping("/articles")
    public String showAll(Model model){
        List<ArticleDto> articleDtos = articleService.showAll();
        model.addAttribute("articles",articleDtos);
        return "article/articles";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow();
        model.addAttribute("article",article);
        //편집 페이지 만들어야 함
        return "article/editArticle";
    }
    @PostMapping("/articles/update")
    public String update(ArticleDto dto){
        Article entity = dto.toEntity();
        Article article = articleRepository.findById(entity.getId()).orElseThrow();
        if (article != null){
            articleRepository.save(entity);
        }
        return "redirect:/articles/" + entity.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElseThrow();
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제완료");
        }
        return "redirect:/articles";
    }
}
