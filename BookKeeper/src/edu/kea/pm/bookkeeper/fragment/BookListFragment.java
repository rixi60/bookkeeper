package edu.kea.pm.bookkeeper.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.database.BookListAdapter;
import edu.kea.pm.bookkeeper.database.Database;

public class BookListFragment extends ListFragment
{
	View rootView;
	BookListAdapter adapter;
	Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
        	rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        }
//        database = new JesperDatabase();
//        adapter = new BookListAdapter(getActivity(), database.getAllBooks());
//        setListAdapter(adapter);
        return rootView;
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
      super.onListItemClick(l, v, pos, id);
      Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
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
