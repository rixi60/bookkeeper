package edu.kea.pm.bookkeeper.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.activity.BookSavedInfoActivity;
import edu.kea.pm.bookkeeper.database.BookListAdapter;
import edu.kea.pm.bookkeeper.database.BookTable;
import edu.kea.pm.bookkeeper.database.Database;
import edu.kea.pm.bookkeeper.database.DatabaseImpl;
import edu.kea.pm.bookkeeper.model.Book;

public class BookListFragment extends ListFragment implements OnItemLongClickListener
{
	View rootView;
	BookListAdapter adapter;
	Database database;
	Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
        	rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        }
        database = new DatabaseImpl(getActivity());
        return rootView;
    }
    
    @Override
    public void onResume()
    {
    	updateList();
    	getListView().setOnItemLongClickListener(this);
    	super.onResume();
    }
    
    private void updateList() {
    	cursor = database.getAllBooks();
    	adapter = new BookListAdapter(getActivity(), cursor);
    	setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
      super.onListItemClick(l, v, pos, id);
      int bookId = cursor.getInt(cursor.getColumnIndex(BookTable.ID));
      Book book = database.getBookWithId(bookId);
      Intent intent = new Intent(getActivity(), BookSavedInfoActivity.class);
      intent.putExtra(BookSavedInfoActivity.BUNDLE_BOOK, book);
      getActivity().startActivity(intent);
    }

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int pos, long id)
	{
		int bookId = cursor.getInt(cursor.getColumnIndex(BookTable.ID));
		final Book book = database.getBookWithId(bookId);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.action_delete)
		.setMessage(R.string.delete_confirm)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				database.deleteBook(book);
				updateList();
			}
		})
		.setNegativeButton(android.R.string.cancel, null);
		builder.create().show();
		return true;
	}
    
}