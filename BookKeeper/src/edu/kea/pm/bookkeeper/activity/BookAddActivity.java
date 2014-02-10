package edu.kea.pm.bookkeeper.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.database.Database;
import edu.kea.pm.bookkeeper.database.DatabaseImpl;
import edu.kea.pm.bookkeeper.fragment.BookAddFragment;
import edu.kea.pm.bookkeeper.fragment.BookAddFragment.BookAddFragmentController;
import edu.kea.pm.bookkeeper.model.Book;


public class BookAddActivity extends FragmentActivity implements BookAddFragmentController
{
	private Book mBook;
	private BookAddFragment mFragment;
	private Database mDatabase;
	public static final String BUNDLE_BOOK = "BUNDLE_BOOK";
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_single_frame_container);
		
		mBook = (Book) getIntent().getSerializableExtra(BUNDLE_BOOK);
		mDatabase = new DatabaseImpl(this);
		
		mFragment = new BookAddFragment();
		mFragment.setArguments(bundle);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        
	}

	@Override
	public Book getBook()
	{
		return mBook;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	// Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_save:
        	Toast.makeText(this, R.string.book_saves, Toast.LENGTH_LONG).show();
        	mDatabase.saveBook(mBook);
            setResult(RESULT_OK);
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	
}
