package com.santhosh.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.santhosh.model.Article;
import com.santhosh.model.Category;
import com.santhosh.utils.Constants;
import com.santhosh.utils.DBConnection;

public class ArticleDao {
	private String errorMessage;

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		String query = "SELECT * FROM categories";

		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public String addArticle(int categoryId, String title, String contentFilePath, String imageFilePath,
			int submitted_by) {
		String query = "INSERT INTO article (category_id,title, content_path, image_path,submitted_by) VALUES (?,?,?, ?, ?)";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryId);
			pstmt.setString(2, title);
			pstmt.setString(3, contentFilePath);
			pstmt.setString(4, imageFilePath);
			pstmt.setInt(5, submitted_by);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return Constants.SUCCESS;
			} else {
				setErrorMessage("Failed to add Article.. Try Again !");
				return Constants.ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Unexpected Error Occured.. Try Again!");
			return Constants.UNXERROR;
		}
	}

	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		String query = "SELECT article.* , users.username FROM article JOIN users ON article.submitted_by=users.id ORDER BY article.id DESC";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setCategoryId(rs.getInt("category_id"));
				article.setTitle(rs.getString("title"));
				String content = getContentFromFile(rs.getString("content_path"));
				article.setContent(content);
				article.setImagePath(rs.getString("image_path"));
				article.setSubmittedBy(rs.getInt("submitted_by"));
				article.setUsername(rs.getString("username"));

				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Unexpected Error Occured.. Try Again!");
		}
		return articles;
	}

	public Article getArticleById(int id) {
		Article article = new Article();
		String query = "SELECT article.*, users.username FROM article JOIN users ON article.submitted_by = users.id WHERE article.id = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				article.setId(rs.getInt("id"));
				article.setCategoryId(rs.getInt("category_id"));
				article.setTitle(rs.getString("title"));
				String content = getContentFromFile(rs.getString("content_path"));
				article.setContent(content);
				article.setImagePath(rs.getString("image_path"));
				article.setSubmittedBy(rs.getInt("submitted_by"));
				article.setUsername(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Unexpected Error Occured.. Try Again!");
		}
		return article;
	}

	public List<Article> getArticlesByUserId(int id) {
		List<Article> articles = new ArrayList<>();
		String query = "SELECT article.*, users.username FROM article JOIN users ON article.submitted_by = users.id WHERE article.submitted_by = ? ORDER BY article.id DESC";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setCategoryId(rs.getInt("category_id"));
				article.setTitle(rs.getString("title"));
				String content = getContentFromFile(rs.getString("content_path"));
				article.setContent(content);
				article.setImagePath(rs.getString("image_path"));
				article.setSubmittedBy(rs.getInt("submitted_by"));
				article.setUsername(rs.getString("username"));

				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("Unexpected Error Occured.. Try Again!");
		}
		return articles;
	}

	private String getContentFromFile(String contentFilePath) throws FileNotFoundException, IOException {
		File file = new File(contentFilePath);
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
