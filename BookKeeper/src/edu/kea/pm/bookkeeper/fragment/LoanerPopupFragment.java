package edu.kea.pm.bookkeeper.fragment;


import edu.kea.pm.bookkeeper.R;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoanerPopupFragment extends DialogFragment
{
		private String loaner = "";
		private EditText loaner_EditText;
		private Bundle bundle;
		
		public LoanerPopupFragment()
		{
		
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
		{
			this.bundle = savedInstanceState; //sets bundle
			this.loaner = this.bundle.getString("loaner"); //grabs string tagged 'loaner' from bundle
			
			View view = inflater.inflate(R.layout.loaner_popup , container);
			this.loaner_EditText = (EditText) view.findViewById(R.id.loaner_text); //connects to editText area in xml
			this.loaner_EditText.setText(this.loaner); //sets the String loaner as text
			
			
	        Button okButton = (Button) view.findViewById(R.id.loaner_ok_button); //connects to XML OK button
	    	okButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					loaner = loaner_EditText.getText().toString(); //replaces the old String with whatever was written in Edittext
					bundle.putString("loaner", loaner);
					getDialog().dismiss(); //closes Dialog Window
				}
	    	});
			
			
	        return view;
	    

	}

}
