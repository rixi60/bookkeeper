package edu.kea.pm.bookkeeper.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.model.Book;

public class BookInfoFragment extends Fragment
{
	
	public interface BookInfoFragmentController {
		public Book getBook();
	}


	private BookInfoFragmentController mListener;

    public BookInfoFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_look_up, container, false);
        
        Book book = mListener.getBook();
        
        return rootView;
    }
    
    
    @Override
    public void onAttach(Activity activity) {
      super.onAttach(activity);

      // This makes sure that the container activity has implemented
      // the callback interface. If not, it throws an exception
      try {
          mListener = (BookInfoFragmentController) activity;
      } catch (ClassCastException e) {
          throw new ClassCastException(activity.toString()
                  + " must implement "+BookInfoFragmentController.class.getSimpleName());
      }
  }

}
