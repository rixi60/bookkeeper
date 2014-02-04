package edu.kea.pm.bookkeeper.model;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.*;



public class BookAPI 
{

	private String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
	private String isbn;
	

	
	public Book readISBN(String isbn) throws Exception {
	    BufferedReader reader = null;
	 
	    
	    String apiurl = this.url+isbn;
	    	
	    //reads the json
	    
	    System.out.println("Her starter vi:");
	    
	    try {
	        URL url = new URL(apiurl);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
	        
	        try {
	        	//JSONArray json = new JSONArray(buffer.toString());
	            JSONObject json = new JSONObject(buffer.toString());
	            JSONArray array = json.getJSONArray("items");
	            
	            JSONObject book;
	            if(array.length() > 0)
	            {
	            book = array.getJSONObject(0);
	            
	            JSONObject volumeInfo = book.getJSONObject("volumeInfo");
	            
	            //System.out.println("Book="+book.getJSONObject("volumeInfo"));
	            Book book1 = new Book();
	            
	            //ISBN
	            String isbn2 = book.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
	            System.out.println(isbn2);
	            book1.setIsbn(isbn2);
	            
	            //TITLE
	            String title = volumeInfo.getString("title");
	            System.out.println(title);
	            book1.setTitle(title);
	            
	            //AUTHORS
	            JSONArray authors = volumeInfo.getJSONArray("authors");
	            for (int i = 0; i < authors.length() ; i++)
	            {
	            	System.out.println(authors.getString(i));
	            	book1.addAuthor(authors.getString(i));
	            }
	            
	            //Description
	            System.out.println(volumeInfo.getString("description"));
	            book1.setDescription(volumeInfo.getString("description"));
	            
	            //Language
	            System.out.println(volumeInfo.getString("language"));	           
	            book1.setLanguage(volumeInfo.getString("language"));
	            
	            //Page Count	       
	            System.out.println(volumeInfo.getInt("pageCount"));
	            book1.setPageCount(volumeInfo.getInt("pageCount"));
	            
	            //Published
	            System.out.println(volumeInfo.getInt("publishedDate"));
	            book1.setPublished(volumeInfo.getInt("publishedDate"));
	            
	            //ThumbnailURL
	            String thumbURL = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
	            System.out.println(thumbURL);
	            book1.setThumbnailURL(thumbURL);
	            
	            
	            
	            book1.setLoaner("Jeppe");
	            book1.setComment("String");
	            
	            
	            return book1;
	            
	            } else
	            	return null;
	            

	         
	            
	            

	        } catch (JSONException e) {
	            e.printStackTrace();
	            
	        }
	        
	        
	        return null;
	    } finally {
	        if (reader != null)
	            reader.close();
	    } 
	    
	    
	}
	
	
}
