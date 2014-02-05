package edu.kea.pm.bookkeeper.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.model.Book;
import edu.kea.pm.bookkeeper.fragment.BookInfoFragment;


public class BookInfoActivity extends FragmentActivity
{
	Book mBook;
	BookInfoFragment mFragment;
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_single_frame_container);
		mBook = (Book) bundle.get(Book.BOOK_BUNDLE_KEY);
		
		mFragment = new BookInfoFragment();
		mFragment.setArguments(bundle);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
	}
	
}
