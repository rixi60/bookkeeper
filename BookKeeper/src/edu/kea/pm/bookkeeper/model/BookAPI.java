package edu.kea.pm.bookkeeper.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookAPI
{
	private static final String TAG = BookAPI.class.getSimpleName();
	private String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

	public Book readISBN(String isbn) throws Exception
	{
		BufferedReader reader = null;

		String apiurl = this.url + isbn;

		// reads the json
		try
		{
			URL url = new URL(apiurl);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			try
			{
				JSONObject json = new JSONObject(buffer.toString());
				JSONArray array = json.getJSONArray("items");

				JSONObject book;
				if (array.length() > 0)
				{
					book = array.getJSONObject(0);

					JSONObject volumeInfo = book.has("volumeInfo") ? book.getJSONObject("volumeInfo") : null;
					
					// Log.d(TAG,"Book="+book.getJSONObject("volumeInfo"));
					Book book1 = new Book();
					
					if (volumeInfo != null) {
						// ISBN
						try
						{
							String isbn2 = volumeInfo.getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
							book1.setIsbn(isbn2);
						}
						catch (Exception e)	{ }
						
						// TITLE
						book1.setTitle(volumeInfo.getString("title"));
						
						// AUTHORS
						JSONArray authors = (volumeInfo.has("authors"))? volumeInfo.getJSONArray("authors") : new JSONArray();
						for (int i = 0; i < authors.length(); i++)
						{
							book1.addAuthor(authors.getString(i));
						}
						
						// Description
						if(volumeInfo.has("description")) {							
							book1.setDescription(volumeInfo.getString("description"));
						}
						
						// Language
						if(volumeInfo.has("language")) {
							book1.setLanguage(volumeInfo.getString("language"));							
						}
						
						// Page Count
						if(volumeInfo.has("pageCount")) {
							book1.setPageCount(volumeInfo.getInt("pageCount"));
						}
						
						// Published
						if(volumeInfo.has("publishedDate"))
						{
							book1.setPublished(volumeInfo.getInt("publishedDate"));
						}
						
						// ThumbnailURL
						String thumbURL = (volumeInfo.has("imageLinks")) ? volumeInfo.getJSONObject("imageLinks").getString("thumbnail") : null;
						book1.setThumbnailURL(thumbURL);
						
						return book1;
					}
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		finally
		{
			if (reader != null)
				reader.close();
		}

	}

}
