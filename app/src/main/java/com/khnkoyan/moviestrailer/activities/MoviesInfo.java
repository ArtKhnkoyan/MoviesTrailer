package com.khnkoyan.moviestrailer.activities;

import android.support.v4.app.Fragment;

import com.khnkoyan.moviestrailer.fragments.MoviesInfoFragment;

public class MoviesInfo extends BaseActivity {

    @Override
    public Fragment createFragment() {
        return MoviesInfoFragment.newInstance();
    }
}
