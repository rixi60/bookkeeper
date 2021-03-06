package edu.kea.pm.bookkeeper.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import edu.kea.pm.bookkeeper.model.Book;
import edu.kea.pm.bookkeeper.model.BookAPI;

public class DownloadBooksTask extends AsyncTask<String, Void, Book>{

	private DownloadListener listener;
	
	public interface DownloadListener {
		public void onBusy();
		public void onFinish(Book book);
		
	}
	
	public DownloadBooksTask(){
		
	}
	public DownloadBooksTask(DownloadListener listener) {
		super();
		this.listener = listener;
	}
	
	@Override
	protected void onPreExecute() { 
		super.onPreExecute();
		if (listener != null) {
			listener.onBusy();
		}
	}
	
	@Override
	protected Book doInBackground(String... params) {
		if ((params == null) || (params.length != 1)) {
			throw new IllegalArgumentException("The URL is expected as a parameter.");
		}
		 try
		{
			// Fetches book information:
			return BookAPI.readISBN(params[0]);

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Book book) {
		super.onPostExecute(book);
		if (listener != null) {
			listener.onFinish(book);
		}
	}
	
	protected InputStream download(String urlString) throws IOException {
	    HttpURLConnection conn = (HttpURLConnection) (new URL(urlString)).openConnection();
	    conn.setReadTimeout(10000);
	    conn.setConnectTimeout(15000);
	    conn.setRequestMethod("GET");
	    conn.setDoInput(true);
	    
	    conn.connect();
	    return conn.getInputStream();      
	}
}
