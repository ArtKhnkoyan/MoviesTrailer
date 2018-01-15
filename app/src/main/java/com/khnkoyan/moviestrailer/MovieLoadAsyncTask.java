package com.khnkoyan.moviestrailer;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.khnkoyan.moviestrailer.fragments.MoviesInfoFragment;
import com.khnkoyan.moviestrailer.models.Movie;

import java.util.List;

public class MovieLoadAsyncTask extends AsyncTask<Void, Void, List<Movie>> {
    private MoviesInfoFragment fragment;
    private ProgressDialog dialog;

    public MovieLoadAsyncTask(MoviesInfoFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(fragment.getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");
        dialog.show();
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        return new MovieLoad().getMoviesList();
    }

    @Override
    protected void onPostExecute(List<Movie> movieList) {
        super.onPostExecute(movieList);
        dialog.dismiss();
        if (fragment != null) {
            fragment.setupAdapter(movieList);
        }
    }
}
