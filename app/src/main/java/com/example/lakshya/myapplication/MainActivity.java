package com.example.lakshya.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lakshya.myapplication.Fragments.BookmarkFragment;
import com.example.lakshya.myapplication.Fragments.FeedFragment;
import com.example.lakshya.myapplication.Fragments.TrendingFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_trending:
                    fragmentManager.beginTransaction().replace(R.id.content,new TrendingFragment()).commit();
                   break;
                case R.id.navigation_feed:
                    fragmentManager.beginTransaction().replace(R.id.content,new FeedFragment()).commit();
                    break;
                case R.id.navigation_bookmarked:
                    fragmentManager.beginTransaction().replace(R.id.content,new BookmarkFragment()).commit();
                    break;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,new TrendingFragment()).commit();
    }

}
