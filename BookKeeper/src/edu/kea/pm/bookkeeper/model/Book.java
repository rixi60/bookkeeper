package edu.kea.pm.bookkeeper.model;

import java.io.Serializable;
import java.util.List;

import android.text.TextUtils;

public class Book implements Serializable
{

	private static final long serialVersionUID = -399086889791219587L;

	public final static String BOOK_BUNDLE_KEY = "BOOK_BUNDLE_KEY";
	private long book_id;
	private String isbn;
	private String title;
	private String authors;
	private String description;
	private String language;
	private int pageCount;
	private String published;
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
	public String getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Adds an author to the book.
	 * @param authors the authors to add
	 */
	public void addAuthor(String author)
	{
		if (TextUtils.isEmpty(this.authors)) {
			this.authors = author;
		} else {
			this.authors += ", "+author;
		}
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
	public String getPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(String published) {
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

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
	
	@Override
	public String toString()
	{
		return "Book [book_id=" + book_id + ", isbn=" + isbn + ", title=" + title + ", authors=" + authors + ", description=" + description + ", language=" + language + ", pageCount=" + pageCount + ", published=" + published + ", thumbnailURL="
				+ thumbnailURL + ", loaner=" + loaner + ", comment=" + comment + "]";
	}
	
}
