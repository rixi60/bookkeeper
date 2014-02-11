package edu.kea.pm.bookkeeper.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.fragment.BookInfoFragment;
import edu.kea.pm.bookkeeper.fragment.BookInfoFragment.BookInfoFragmentController;
import edu.kea.pm.bookkeeper.http.DownloadBooksTask;
import edu.kea.pm.bookkeeper.http.DownloadBooksTask.DownloadListener;
import edu.kea.pm.bookkeeper.model.Book;


public class BookInfoActivity extends FragmentActivity implements BookInfoFragmentController, DownloadListener
{
	private Book mBook;
	private BookInfoFragment mFragment;
	private String mBarcode;
	public static final String BUNDLE_BARCODE = "BUNDLE_BARCODE";
	private static final int ADD_BOOK_REQUEST_CODE = 2;
	public DownloadBooksTask mTask;
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(bundle);
		setContentView(R.layout.activity_single_frame_container);
		
		mBook = new Book();
		mBarcode = getIntent().getStringExtra(BUNDLE_BARCODE);
		
		mFragment = new BookInfoFragment();
		mFragment.setArguments(bundle);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        
       mTask = new DownloadBooksTask(this);
       mTask.execute(mBarcode);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	// Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_add:
            if (mBook != null) {
            	Intent intent = new Intent(this, BookAddActivity.class);
            	intent.putExtra(BookAddActivity.BUNDLE_BOOK, mBook);
                startActivityForResult(intent, ADD_BOOK_REQUEST_CODE);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_add).setVisible(mBook.getIsbn() != null);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	if (requestCode == ADD_BOOK_REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			finish();
    		}
    	}
    }
	
	
	@Override
	protected void onStop()
	{
		mTask.cancel(true);
		super.onPause();
	}

	@Override
	public Book getBook()
	{
		return mBook;
	}

	@Override
	public void onBusy()
	{
		invalidateOptionsMenu();
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
					.setMessage(getString(R.string.book_not_found, mBarcode))
					.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							BookInfoActivity.this.finish();
						}
					});
			builder.create().show();
		}
		invalidateOptionsMenu();
	}
}
