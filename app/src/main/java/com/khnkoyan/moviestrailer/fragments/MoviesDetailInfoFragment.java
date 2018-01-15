package com.khnkoyan.moviestrailer.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.khnkoyan.moviestrailer.R;
import com.khnkoyan.moviestrailer.activities.MoviesTrailerActivity;
import com.khnkoyan.moviestrailer.models.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesDetailInfoFragment extends Fragment {
    private ImageView imgViewAvengers;
    private TextView txtTitleMovieDet;
    private TextView txtTagLineMovieDet;
    private TextView txtDateMovieDet;
    private TextView txtDurationMovieDet;
    private TextView txtDirNameMovieDet;
    private RatingBar ratMoveDetails;
    private TextView txtCastMovieDet;
    private TextView txtStoryMovieDet;
    private ProgressBar progressMovieDet;

    public MoviesDetailInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_deatil_infi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpUIViews(view);

        setDataInView();
        imgViewAvengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoviesTrailerActivity.class);
                String moviesTitle = txtTitleMovieDet.getText().toString();
                intent.putExtra("moviesTitle", moviesTitle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setUpUIViews(View view) {
        imgViewAvengers = (ImageView) view.findViewById(R.id.imgViewAvengers);
        txtTitleMovieDet = (TextView) view.findViewById(R.id.txtTitleMovieDet);
        txtTagLineMovieDet = (TextView) view.findViewById(R.id.txtTagLineMovieDet);
        txtDateMovieDet = (TextView) view.findViewById(R.id.txtDateMovieDet);
        txtDurationMovieDet = (TextView) view.findViewById(R.id.txtDurationMovieDet);
        txtDirNameMovieDet = (TextView) view.findViewById(R.id.txtDirNameMovieDet);
        ratMoveDetails = (RatingBar) view.findViewById(R.id.ratMoveDetails);
        txtCastMovieDet = (TextView) view.findViewById(R.id.txtCastMovieDet);
        txtStoryMovieDet = (TextView) view.findViewById(R.id.txtStoryMovieDet);
        progressMovieDet = (ProgressBar) view.findViewById(R.id.progressMovieDet);


    }

    private void setDataInView() {
        Bundle bundle = getActivity().getIntent().getExtras();
        String json = bundle.getString("movie");
        Movie movie = new Gson().fromJson(json, Movie.class);
        progressMovieDet.setVisibility(View.VISIBLE);
        Picasso.with(getActivity()).load(movie.getImage()).into(imgViewAvengers, new Callback() {
            @Override
            public void onSuccess() {
                progressMovieDet.setVisibility(View.GONE);
                imgViewAvengers.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                progressMovieDet.setVisibility(View.GONE);
                imgViewAvengers.setVisibility(View.INVISIBLE);
            }
        });
        progressMovieDet.setVisibility(View.GONE);
        txtTitleMovieDet.setText(movie.getMovie());
        txtTagLineMovieDet.setText(movie.getTagline());
        txtDateMovieDet.setText("Year: " + movie.getYear());
        txtDurationMovieDet.setText("Duration:" + movie.getDuration());
        txtDirNameMovieDet.setText("Director:" + movie.getDirector());

        // rating bar
        ratMoveDetails.setRating(movie.getRating());

        StringBuffer stringBuffer = new StringBuffer();
        for (Movie.Cast cast : movie.getCastList()) {
            stringBuffer.append(cast.getName() + ", ");
        }

        txtCastMovieDet.setText("Cast:" + stringBuffer);
        txtStoryMovieDet.setText(movie.getStory());

    }
}
