package com.example.bookkeeper;

public class Book 
{
	private String isbn;
	private String title;
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
	
	
}
