package com.example.jeff.butterfly.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.example.jeff.butterfly.Helpers.DbHelper;
import com.example.jeff.butterfly.Model.Transaction;
import com.example.jeff.butterfly.R;

public class LandingFragment extends Fragment {
    
	private DbHelper db;
	private View view;
	private Activity activity;

	private EditText title;
	private EditText body;
	private Button button;
	private Switch slider;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		 db = new DbHelper(this.getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.activity_main, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		view = getView();
		activity = (AppCompatActivity)getActivity();
		quoteOfDay();
		getButtons();
	}

	private void quoteOfDay(){
		TextView quote = view.findViewById(R.id.quote);
		Transaction something = db.getRandomPost();
		quote.setText(something.getTitle());
	}

	private void getButtons(){
		title = view.findViewById(R.id.title);
		body = view.findViewById(R.id.body);
		button = view.findViewById(R.id.button3);
		slider = view.findViewById(R.id.switch1);

		button.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Editable wow = (Editable)title.getText();
				Editable how = (Editable)body.getText();
				String titleWords = wow.toString();
				String bodyWords = how.toString();
				Boolean switchState = slider.isChecked();
				int stateSwitch;
				if (switchState == true){
					stateSwitch = 1;
				}
				else {
					stateSwitch = 0;
				}

				// Log.e("------------------------------", titleWords+"_"+bodyWords+"_"+stateSwitch);
				// db.dbStuff();
				db.makeTransaction(titleWords, bodyWords, stateSwitch);
			}
		});
	}

}
