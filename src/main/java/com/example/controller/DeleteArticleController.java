package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事とコメントの処理を制御するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/delete-article")
public class DeleteArticleController {

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
//			return index(model);
//		}
//
//		Article article = new Article();
//		BeanUtils.copyProperties(form, article);
//		articleRepository.insert(article);
//		return "redirect:/article";
//	}

//	/**
//	 * コメントを作成する.
//	 * 
//	 * @param form 記事のリクエストスコープを受け取るフォーム
//	 * @return 掲示板画面
//	 */
//	@RequestMapping("/insert-comment")
//	public String insertComment(CommentForm form) {
//		Comment comment = new Comment();
//		BeanUtils.copyProperties(form, comment);
//		comment.setArticleId(form.getIntArticleId());
//		commentRepository.insert(comment);
//		return "redirect:/article";
//	}

	/**
	 * 記事とコメントを削除する.
	 * 
	 * @param articleId 記事のID
	 * @return 掲示板画面
	 */
	@RequestMapping("/delete-article")
	public String deleteArticle(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		return "redirect:/show-bbs";
	}
}
