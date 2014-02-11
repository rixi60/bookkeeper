package edu.kea.pm.bookkeeper.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.model.Book;

public class BookAddFragment extends Fragment
{
	
	TextView mIsbn;
	EditText mTitle;
	EditText mAuthor;
	EditText mLanguage;
	EditText mDescription;
	EditText mPages;
	EditText mPublished;
	EditText mCoverImageUrl;
	EditText mComment;
	
	public interface BookAddFragmentController {
		public Book getBook();
	}


	private BookAddFragmentController mListener;

    public BookAddFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_edit, container, false);
        mIsbn = (TextView) rootView.findViewById(R.id.isbn);
        mTitle = (EditText) rootView.findViewById(R.id.editTitle);
        mAuthor = (EditText) rootView.findViewById(R.id.editAuthor);
        mLanguage = (EditText) rootView.findViewById(R.id.Language);
        mDescription = (EditText) rootView.findViewById(R.id.editDesc);
        mPages = (EditText) rootView.findViewById(R.id.editNumberOfPages);
        mPublished = (EditText) rootView.findViewById(R.id.editPublished);
        mCoverImageUrl = (EditText) rootView.findViewById(R.id.editCoverImgThumbnailURL);
        mComment = (EditText) rootView.findViewById(R.id.editComments);
        updateBook();
        return rootView;
    }
    
    public void updateBook() {
    	Book book = mListener.getBook();
    	String noInfo = "";
    	mIsbn.setText(book.getIsbn() != null ? book.getIsbn() : noInfo);
    	mTitle.setText(book.getTitle() != null ? book.getTitle() : noInfo);
    	mAuthor.setText(book.getAuthors() != null ? book.getAuthors().toString() : noInfo);
    	mLanguage.setText(book.getLanguage() != null ? book.getLanguage() : noInfo);
    	mDescription.setText(book.getDescription() != null ? book.getDescription() : noInfo);
    	mPages.setText(book.getPageCount() > 0 ? String.valueOf(book.getPageCount()) : noInfo);
    	mPublished.setText(!TextUtils.isEmpty(book.getPublished()) ? book.getPublished() : noInfo);
    	mCoverImageUrl.setText(!TextUtils.isEmpty(book.getThumbnailURL()) ? book.getThumbnailURL() : noInfo);
    	mComment.setText(book.getComment() != null ? book.getComment() : noInfo);
//    	mCoverImage // TODO: Get image from URL
    }
    
    public Book getBookInfo() {
    	Book book = mListener.getBook();
    	Book newBook = new Book();
    	newBook.setBook_id(book.getBook_id());
    	newBook.setLoaner(book.getLoaner());
    	newBook.setAuthors(mAuthor.getText().toString());
    	newBook.setTitle(mTitle.getText().toString());
    	newBook.setLanguage(mLanguage.getText().toString());
    	newBook.setDescription(mDescription.getText().toString());
    	newBook.setPageCount(Integer.parseInt(mPages.getText().toString()));
    	newBook.setPublished(mPublished.getText().toString());
    	newBook.setThumbnailURL(mCoverImageUrl.getText().toString());
    	newBook.setComment(mComment.getText().toString());
    	return newBook;
    }
    
    
    @Override
    public void onAttach(Activity activity) {
      super.onAttach(activity);

      // This makes sure that the container activity has implemented
      // the callback interface. If not, it throws an exception
      try {
          mListener = (BookAddFragmentController) activity;
      } catch (ClassCastException e) {
          throw new ClassCastException(activity.toString()
                  + " must implement "+BookAddFragmentController.class.getSimpleName());
      }
  }

}
