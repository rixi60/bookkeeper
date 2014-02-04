package edu.kea.pm.bookkeeper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import edu.kea.pm.bookkeeper.R;

public class LoopUpFragment extends Fragment
{
	
	EditText mTextField;
	Button mButtonScan;
	Button mButtonLookUp;

    public LoopUpFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_look_up, container, false);
        mTextField = (EditText) rootView.findViewById(R.id.isbnEditText);
        mButtonLookUp = (Button) rootView.findViewById(R.id.buttonLookUp);
        mButtonScan = (Button) rootView.findViewById(R.id.buttonScan);
        
        mButtonLookUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				
			}
		});
        
        mButtonScan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				
			}
		});
        
        
        return rootView;
    }
    
    private void openBookForISBN(){
    	
    }
}
