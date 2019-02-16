package com.example.jeff.butterfly;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import com.example.jeff.butterfly.Fragments.LandingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initpage);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new LandingFragment(), "main")
                .addToBackStack(null)
                .commit();
    }



}
