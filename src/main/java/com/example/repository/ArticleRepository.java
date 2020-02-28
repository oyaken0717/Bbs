package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author yamadadai
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * articleオブジェクトを生成するRowMapper.
	 */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};

	/**
	 * articlesテーブルの表を参照したResultSetExtractor.
	 */
	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;

		int beforeArticleId = 0;

		while (rs.next()) {
			int nowArticleId = rs.getInt("id");
			if (nowArticleId != beforeArticleId) {
				Article article = new Article();
				article.setId(nowArticleId);
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);

				articleList.add(article);

			}

			if (rs.getInt("com_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));

				commentList.add(comment);
			}

			beforeArticleId = nowArticleId;
		}

		return articleList;
	};

	/**
	 * 記事情報を全件検索.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll() {
		String sql = "SELECT a.id,a.name,a.content,c.id com_id,c.name com_name,c.content com_content,c.article_id "
				+ "FROM articles a LEFT OUTER JOIN comments c on a.id = c.article_id ORDER BY id DESC";

		return template.query(sql, ARTICLE_RESULT_SET);
	}

	/**
	 * 記事情報を挿入.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);

		String sql = "INSERT INTO articles(name, content) values(:name, :content)";

		template.update(sql, param);
	}

	/**
	 * 記事情報を削除.
	 * 
	 * @param id ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM articles WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(sql, param);
	}
}

