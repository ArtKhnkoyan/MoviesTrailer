package com.khnkoyan.moviestrailer.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.khnkoyan.moviestrailer.R;
import com.khnkoyan.moviestrailer.fragments.MoviesDetailInfoFragment;

public class MoviesDetail extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragContainer);
        if (fragment == null) {
            fragment = new MoviesDetailInfoFragment();
            fm.beginTransaction().add(R.id.fragContainer, fragment, "MoviesInfoFragment")
                    .commit();
        }
    }
}
