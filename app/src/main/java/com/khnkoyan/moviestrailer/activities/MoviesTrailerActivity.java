package com.khnkoyan.moviestrailer.activities;

import android.support.v4.app.Fragment;

import com.khnkoyan.moviestrailer.fragments.MoviesTrailerFragment;

public class MoviesTrailerActivity extends BaseActivity {

    @Override
    public Fragment createFragment() {
        return new MoviesTrailerFragment();
    }

}
