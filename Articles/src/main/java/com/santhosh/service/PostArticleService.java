package com.santhosh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.santhosh.dao.ArticleDao;
import com.santhosh.dao.UserDao;
import com.santhosh.model.Category;
import com.santhosh.model.User;
import com.santhosh.utils.Constants;

@SuppressWarnings("serial")
public class PostArticleService extends ActionSupport {

	private int category;
	private String title;
	private String content;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private String errorMessage;
	private List<Category> categories;
	private User user;
	private ArticleDao articleDao = new ArticleDao();
	private UserDao userDao = new UserDao();

	@Override
	public String execute() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		try {
			String userEmail = (String) ActionContext.getContext().getSession().get("userEmail");
			setUser(userDao.retrieveDetails(userEmail));
			if (user == null) {
				setErrorMessage("User not found. Please log in.");
				return ERROR;
			}

			String userDir = user.getUsername();
			String projectDir = "D:/Eclipse/Articles/src/main/webapp";
			File imageFolder = new File(projectDir + "/images/" + userDir);
			File contentFolder = new File(projectDir + "/contents/" + userDir);

			if (!imageFolder.exists() && !imageFolder.mkdirs()) {
				setErrorMessage("Failed to create image directory.");
				return ERROR;
			}
			if (!contentFolder.exists() && !contentFolder.mkdirs()) {
				setErrorMessage("Failed to create content directory.");
				return ERROR;
			}

			String contentFilePath = projectDir + "/contents/" + userDir + "/" + title + ".txt";
			File destFile = new File(imageFolder, imageFileName);

			FileUtils.copyFile(image, destFile);
			FileUtils.writeStringToFile(new File(contentFilePath), content, "UTF-8");

			String status = articleDao.addArticle(category, title, contentFilePath,
					"images/" + userDir + "/" + imageFileName, user.getId());
			return status;
		} catch (IOException e) {
			e.printStackTrace();
			setErrorMessage("Unexpected error occurred. Please try again.");
		}
		return ERROR;
	}

	public String redirectPostArticle() {
		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		return fetchCategories();
	}

	public String fetchCategories() {
		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		setCategories(articleDao.getCategories());
		if (!categories.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	};

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
