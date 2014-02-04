package edu.kea.pm.bookkeeper.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.kea.pm.bookkeeper.R;

public class BookListFragment extends ListFragment
{
	View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
        	rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        }
        return rootView;
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
    	// TODO Auto-generated method stub
    	super.onSaveInstanceState(outState);
    }
    
    private void onRestoreInstaceState()
	{
		// TODO Auto-generated method stub

	}
}
