package com.santhosh.service;

import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.santhosh.dao.ArticleDao;
import com.santhosh.model.Article;
import com.santhosh.utils.Constants;

@SuppressWarnings("serial")
public class HomeService extends ActionSupport {
	private int id;
	private Article article;
	private String errorMessage;
	private List<Article> articles;
	private ArticleDao articleDao = new ArticleDao();

	@Override
	public String execute() {

		if (!Constants.checkAuthentication()) {
			addActionError("User should be Logged in to Access..!");
			return Constants.UNAUTH;
		}

		setArticles(articleDao.getAllArticles());

		if (articles.isEmpty()) {
			ServletActionContext.getRequest().setAttribute("articles", articles);
			setErrorMessage("No Articles Available..Post Now?");
			return SUCCESS;
		}

		Collections.shuffle(articles);
		ServletActionContext.getRequest().setAttribute("articles", articles);
		return SUCCESS;
	}

	public String viewArticle() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		setArticle(articleDao.getArticleById(id));
		if (article != null) {
			ServletActionContext.getRequest().setAttribute("article", article);
			return SUCCESS;
		}

		return ERROR;
	}

	public String viewUserArticle() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		int id = (Integer) ActionContext.getContext().getSession().get("userId");
		setArticles(articleDao.getArticlesByUserId(id));
		if (articles != null && !articles.isEmpty()) {
			ServletActionContext.getRequest().setAttribute("articles", articles);
			return SUCCESS;
		}
		setErrorMessage("You've not Posted anything!");
		return ERROR;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
