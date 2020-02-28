package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事とコメントの処理を制御するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/insert-comment")
public class InsertCommentController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

//	/**
//	 * 掲示板画面に遷移する.
//	 * 
//	 * @param model リクエストスコープ
//	 * @return 一覧画面
//	 */
//	@RequestMapping("")
//	public String index(Model model) {
//		List<Article> articleList = articleRepository.findAll();
//		model.addAttribute("articleList", articleList);
//		return "index";
//	}

//	/**
//	 * 記事を作成する.
//	 * 
//	 * @param form 記事のリクエストスコープを受け取るフォーム
//	 * @return 掲示板画面
//	 */
//	@RequestMapping("/insert-article")
//	public String insertArticle(@Validated ArticleForm form,BindingResult result, Model model) {
//		String formContent = form.getContent();
//		if (formContent.length() >= 11) {
//			result.rejectValue("content", null, "投稿内容は10文字以内でお願いします");
//		}
//		
//		if (result.hasErrors()) {
////■			return index(model);
////			return "redirect:/show-bbs";
//          return "forward:/show-bbs/";
//		}
//
//		Article article = new Article();
//		BeanUtils.copyProperties(form, article);
//		articleRepository.insert(article);
//		return "redirect:/show-bbs";
//	}

	/**
	 * コメントを作成する.
	 * 
	 * @param form 記事のリクエストスコープを受け取るフォーム
	 * @return 掲示板画面
	 */
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.toIntegerArticleId());
		commentRepository.insert(comment);
		return "redirect:/show-bbs";
	}

//	/**
//	 * 記事とコメントを削除する.
//	 * 
//	 * @param articleId 記事のID
//	 * @return 掲示板画面
//	 */
//	@RequestMapping("/delete-article")
//	public String deleteArticle(Integer articleId) {
//		commentRepository.deleteByArticleId(articleId);
//		articleRepository.deleteById(articleId);
//		return "redirect:/article";
//	}
}
