package edu.kea.pm.bookkeeper.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import edu.kea.pm.bookkeeper.R;

public class LoanerPopupFragment extends DialogFragment
{
		public static final String BUNDLE_LOANER_TEXT = "loaner";
		private EditText loaner_EditText;
		private LoanerPopupFragmentListener listener;
		
		public interface LoanerPopupFragmentListener {
			public void onOK(String text);
		}

		public LoanerPopupFragment()
		{
		
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
		{
			Bundle bundle = getArguments(); //sets bundle
			String loaner = bundle.getString(BUNDLE_LOANER_TEXT, ""); //grabs string tagged 'loaner' from bundle
			getDialog().setTitle(R.string.enter_loaner);
			
			View view = inflater.inflate(R.layout.loaner_popup , container);
			this.loaner_EditText = (EditText) view.findViewById(R.id.loaner_text); //connects to editText area in xml
			this.loaner_EditText.setText(loaner); //sets the String loaner as text
			this.loaner_EditText.setSelection(0, loaner_EditText.length());
			this.loaner_EditText.setOnEditorActionListener(new OnEditorActionListener() {
	            @Override
	            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	                boolean handled = false;
	                if (actionId == EditorInfo.IME_ACTION_DONE && listener != null) {
	                	listener.onOK(loaner_EditText.getText().toString().trim());
	                	LoanerPopupFragment.this.dismiss();
	                    handled = true;
	                }
	                return handled;
	            }
	        });
	        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
	        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
			
			
	        Button okButton = (Button) view.findViewById(R.id.loaner_ok_button); //connects to XML OK button
			
	        okButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v)
				{
					if (listener != null) {
						listener.onOK(loaner_EditText.getText().toString().trim());
				        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
				        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
						LoanerPopupFragment.this.dismiss();
					}
				}
			});
			
	        return view;
	    }
		
		public void setListener(LoanerPopupFragmentListener listener)
		{
			this.listener = listener;
		}
		

}
