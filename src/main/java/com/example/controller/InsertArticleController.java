package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を投稿するコントローラ.
 * 
 * @author yamadadai
 *
 */
@Controller
@RequestMapping("/bbs")
public class InsertArticleController {

	@Autowired
	private ArticleRepository repository;
	
	@ModelAttribute
	public ArticleForm setForm() {
		return new ArticleForm();
	}
	
	/**
	 * 受け取った値を元にデータに格納.
	 * 
	 * @param form フォームクラス
	 * @return　リダイレクトでtoInsertへ
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		repository.insert(article);
		return "redirect:/bbs/toInsert";
	}
}
