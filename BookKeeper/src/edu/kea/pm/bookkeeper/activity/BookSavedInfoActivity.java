package edu.kea.pm.bookkeeper.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.database.Database;
import edu.kea.pm.bookkeeper.database.DatabaseImpl;
import edu.kea.pm.bookkeeper.fragment.BookSavedInfoFragment;
import edu.kea.pm.bookkeeper.fragment.BookSavedInfoFragment.BookSavedInfoFragmentController;
import edu.kea.pm.bookkeeper.fragment.LoanerPopupFragment;
import edu.kea.pm.bookkeeper.fragment.LoanerPopupFragment.LoanerPopupFragmentListener;
import edu.kea.pm.bookkeeper.model.Book;


public class BookSavedInfoActivity extends FragmentActivity implements BookSavedInfoFragmentController
{
	public static final String BUNDLE_BOOK = "BUNDLE_BOOK";
	
	private Book mBook;
	private BookSavedInfoFragment mFragment;
	private Database mDatabase;
	private static final int EDIT_BOOK_REQUEST_CODE = 2;
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_single_frame_container);
		
		mBook = (Book) getIntent().getSerializableExtra(BUNDLE_BOOK);
		mDatabase = new DatabaseImpl(this);
		
		mFragment = new BookSavedInfoFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	// Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_edit:
           	Intent intent = new Intent(this, BookAddActivity.class);
           	intent.putExtra(BookAddActivity.BUNDLE_BOOK, mBook);
            startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            return true;
        case R.id.action_delete:
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_delete)
					.setMessage(R.string.delete_confirm)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							mDatabase.deleteBook(mBook);
							finish();
						}
					})
					.setNegativeButton(android.R.string.cancel, null);
			builder.create().show();
            return true;
        case R.id.action_loan_status_edit:
        	LoanerPopupFragment popup = new LoanerPopupFragment();
        	Bundle bundle = new Bundle();
        	bundle.putString(LoanerPopupFragment.BUNDLE_LOANER_TEXT, mBook.getLoaner());
        	popup.setArguments(bundle);
        	popup.setListener(new LoanerPopupFragmentListener() {
    			
    			@Override
    			public void onOK(String text)
    			{
    				mBook.setLoaner(TextUtils.isEmpty(text) ? null : text);
    				mDatabase.saveBook(mBook);
    				mFragment.updateBook();
    			}
    		});
        	popup.show(getSupportFragmentManager(), null);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	if (requestCode == EDIT_BOOK_REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			finish();
    		}
    	}
    }
	
	

	@Override
	public Book getBook()
	{
		return mBook;
	}

}
