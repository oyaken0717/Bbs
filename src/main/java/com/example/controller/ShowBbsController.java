package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ShowBbsController {
	
	/**
	 * Articleフォームを使えるようにインスタンス化.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	/**
	 * Commentフォームを使えるようにインスタンス化.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	/**
	 * レポジトリのメソッドを利用できるように変数宣言.
	 */
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 掲示板の入力フォーム兼記事掲載画面へ遷移.
	 * 
	 * @param model リクエストスコープ.
	 * @return 掲示板画面.
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);

		return "insert";
	}

}
