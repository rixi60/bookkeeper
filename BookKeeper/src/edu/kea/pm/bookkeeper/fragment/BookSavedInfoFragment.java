package edu.kea.pm.bookkeeper.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import edu.kea.pm.bookkeeper.R;
import edu.kea.pm.bookkeeper.model.Book;

public class BookSavedInfoFragment extends Fragment
{
	
	TextView mIsbn;
	TextView mTitle;
	TextView mAuthor;
	TextView mLanguage;
	TextView mDescription;
	TextView mPages;
	TextView mPublished;
	ImageView mCoverImage;
	
	public interface BookSavedInfoFragmentController {
		public Book getBook();
	}


	private BookSavedInfoFragmentController mListener;
	private View mStatus;
	private TextView mComment;
	private TextView mLoaner;
	private TextView mLoanerLabel;

    public BookSavedInfoFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_info_status, container, false);
        mIsbn = (TextView) rootView.findViewById(R.id.isbn);
        mStatus = rootView.findViewById(R.id.status);
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mAuthor = (TextView) rootView.findViewById(R.id.author);
        mLanguage = (TextView) rootView.findViewById(R.id.language);
        mDescription = (TextView) rootView.findViewById(R.id.desc);
        mPages = (TextView) rootView.findViewById(R.id.pages);
        mPublished = (TextView) rootView.findViewById(R.id.published);
        mComment = (TextView) rootView.findViewById(R.id.note);
        mLoaner = (TextView) rootView.findViewById(R.id.loaner);
        mLoanerLabel = (TextView) rootView.findViewById(R.id.labelLoaner);
        mCoverImage = (ImageView) rootView.findViewById(R.id.imageCover);
        updateBook();
        return rootView;
    }
    
    public void updateBook() {
    	Book book = mListener.getBook();
    	String noInfo = "";
    	boolean loanedOut = TextUtils.isEmpty(book.getLoaner());
    	mStatus.setBackgroundColor(loanedOut ? Color.GREEN : Color.RED);
    	mIsbn.setText(book.getIsbn() != null ? book.getIsbn() : noInfo);
    	mTitle.setText(book.getTitle() != null ? book.getTitle() : noInfo);
    	mAuthor.setText(book.getAuthors() != null ? book.getAuthors().toString() : noInfo);
    	mLanguage.setText(book.getLanguage() != null ? book.getLanguage() : noInfo);
    	mDescription.setText(book.getDescription() != null ? book.getDescription() : noInfo);
    	mPages.setText(book.getPageCount() > 0 ? String.valueOf(book.getPageCount()) : noInfo);
    	mPublished.setText(!TextUtils.isEmpty(book.getPublished()) ? book.getPublished() : noInfo);
    	mComment.setText(!TextUtils.isEmpty(book.getComment()) ? book.getComment() : noInfo);
    	mLoaner.setText(!TextUtils.isEmpty(book.getLoaner()) ? book.getLoaner() : noInfo);
    	mLoaner.setVisibility(!loanedOut ? View.VISIBLE : View.GONE);
    	mLoanerLabel.setVisibility(!loanedOut ? View.VISIBLE : View.GONE);
    	//    	mCoverImage // TODO: Get image from URL
    }
    
    
    @Override
    public void onAttach(Activity activity) {
      super.onAttach(activity);

      // This makes sure that the container activity has implemented
      // the callback interface. If not, it throws an exception
      try {
          mListener = (BookSavedInfoFragmentController) activity;
      } catch (ClassCastException e) {
          throw new ClassCastException(activity.toString()
                  + " must implement "+BookSavedInfoFragmentController.class.getSimpleName());
      }
  }

}
