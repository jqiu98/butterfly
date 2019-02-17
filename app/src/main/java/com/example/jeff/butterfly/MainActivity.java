package com.example.jeff.butterfly;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import com.example.jeff.butterfly.Fragments.LandingFragment;
import com.example.jeff.butterfly.Fragments.PersonalFeedFragment;
import com.example.jeff.butterfly.Fragments.SocialFeedFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initpage);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new LandingFragment());


            // getSupportFragmentManager().beginTransaction()
            //         .add(R.id.fragmentContainer, new LandingFragment(), "main")
            //         .addToBackStack(null)
            //         .commit();
        }

         @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
 
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new LandingFragment();
                break;
 
            case R.id.navigation_dashboard:
                fragment = new SocialFeedFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new PersonalFeedFragment();
                break;
        }
 
        return loadFragment(fragment);
    }

        private boolean loadFragment(Fragment fragment) {
            //switching fragment
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();
                return true;
            }
            return false;
        }
}
