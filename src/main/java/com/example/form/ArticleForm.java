package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * 記事の投稿内容に関するリクエストパラメータを受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class ArticleForm {
	/** 記事のID */
	private String id;
	/** 投稿者名 */
	@NotBlank(message = "投稿者名は必須です")
	private String name;
	/** 投稿内容 */
	@NotBlank(message = "投稿記事は必須です")
	private String content;

	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleForm [id=" + id + ", name=" + name + ", content=" + content + "]";
	}

}
