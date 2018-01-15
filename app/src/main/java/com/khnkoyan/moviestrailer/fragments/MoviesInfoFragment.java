package com.khnkoyan.moviestrailer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khnkoyan.moviestrailer.MovieLoadAsyncTask;
import com.khnkoyan.moviestrailer.R;
import com.khnkoyan.moviestrailer.adapters.MoviesAdapter;
import com.khnkoyan.moviestrailer.models.Movie;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesInfoFragment extends Fragment {
    private RecyclerView recView;

    public MoviesInfoFragment() {
        // Required empty public constructor
    }

    public static MoviesInfoFragment newInstance() {
        return new MoviesInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recView = (RecyclerView) view.findViewById(R.id.recMoviesList);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new MovieLoadAsyncTask(this).execute();
    }

    public void setupAdapter(List<Movie> movieList) {
        if (isAdded()) {
            recView.setAdapter(new MoviesAdapter(getActivity(), movieList));
        }
    }


}
