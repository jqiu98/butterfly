package com.example.jeff.butterfly.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jeff.butterfly.Helpers.DbHelper;
import com.example.jeff.butterfly.R;
import com.example.jeff.butterfly.RecyclerViewAdapter;

public class PersonalFeedFragment extends Fragment {

	private DbHelper db;
	private AppCompatActivity activity;
    private View view;
    private RecyclerView feed;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DbHelper(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.socialpage, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Log.e("----------------------------","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        view = getView();
        activity = (AppCompatActivity) getActivity();
        initRecyclerView();
    }

    private void initRecyclerView(){
        feed = view.findViewById(R.id.socialFeed);
        feed.removeAllViews();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(activity,db.getYourPosts());
        feed.setAdapter(adapter);
        Log.e("----------------------------","ADAPTERSET");
        feed.setLayoutManager(new LinearLayoutManager(activity));
    }
}
