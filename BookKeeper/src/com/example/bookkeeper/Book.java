package com.example.bookkeeper;

import java.util.List;

public class Book 
{
	private String isbn;
	private String title;
	private List<String> authors;
	private String description;
	private String language;
	private int pageCount;
	private int published;
	private String thumbnailURL;
	private String loaner;
	private String comment;
	
	
	
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	public String getIsbn()
	{
		return this.isbn;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	

	/**
	 * @return the authors
	 */
	public List<String> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	/**
	 * Adds an author to the book.
	 * @param authors the authors to add
	 */
	public void addAuthor(String author)
	{
		this.authors.add(author);
	}
	
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the published
	 */
	public int getPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(int published) {
		this.published = published;
	}

	/**
	 * @return the thumbnailURL
	 */
	public String getThumbnailURL() {
		return thumbnailURL;
	}

	/**
	 * @param thumbnailURL the thumbnailURL to set
	 */
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}

	/**
	 * @return the loaner
	 */
	public String getLoaner() {
		return loaner;
	}

	/**
	 * @param loaner the loaner to set
	 */
	public void setLoaner(String loaner) {
		this.loaner = loaner;
	}
	
	
}
