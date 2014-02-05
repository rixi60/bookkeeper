package edu.kea.pm.bookkeeper.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.fragment.BookInfoFragment;
import edu.kea.pm.bookkeeper.fragment.BookInfoFragment.BookInfoFragmentController;
import edu.kea.pm.bookkeeper.http.DownloadBooksTask;
import edu.kea.pm.bookkeeper.http.DownloadBooksTask.DownloadListener;
import edu.kea.pm.bookkeeper.model.Book;


public class BookInfoActivity extends FragmentActivity implements BookInfoFragmentController, DownloadListener
{
	Book mBook;
	BookInfoFragment mFragment;
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(bundle);
		setContentView(R.layout.activity_single_frame_container);
		
		mBook = (Book) getIntent().getSerializableExtra(Book.BOOK_BUNDLE_KEY);
		
		mFragment = new BookInfoFragment();
		mFragment.setArguments(bundle);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        
        new DownloadBooksTask(this).execute(mBook.getIsbn());
	}

	@Override
	public Book getBook()
	{
		return mBook;
	}

	@Override
	public void onBusy()
	{
		setProgressBarIndeterminateVisibility(Boolean.TRUE); 
	}

	@Override
	public void onFinish(Book book)
	{
		setProgressBarIndeterminateVisibility(Boolean.FALSE);
		if (book != null) {
			mBook = book;
			mFragment.updateBook();
		} else {
 
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Error")
					.setOnCancelListener(new OnCancelListener() {
		
						@Override
						public void onCancel(DialogInterface dialog)
						{
							BookInfoActivity.this.finish();
						}
					})
					.setMessage("The book with ISBN "+mBook.getIsbn()+" could not be found.\nCheck the ISBN and your internet connection")
					.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							BookInfoActivity.this.finish();
						}
					});
			builder.create().show();
		}
	}
	
}
