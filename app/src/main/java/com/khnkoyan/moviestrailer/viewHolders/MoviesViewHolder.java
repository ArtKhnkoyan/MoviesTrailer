package com.khnkoyan.moviestrailer.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.khnkoyan.moviestrailer.R;
import com.khnkoyan.moviestrailer.models.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgViewMoves;
    private TextView txtTitle;
    private TextView txtTagLine;
    private TextView txtDate;
    private TextView txtDuration;
    private TextView txtDirName;
    private RatingBar moviesRating;
    private ProgressBar moveProgressBar;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        imgViewMoves = (ImageView) itemView.findViewById(R.id.imgViewMoves);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtTagLine = (TextView) itemView.findViewById(R.id.txtTagLine);
        txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        txtDuration = (TextView) itemView.findViewById(R.id.txtDuration);
        txtDirName = (TextView) itemView.findViewById(R.id.txtDirName);
        moviesRating = (RatingBar) itemView.findViewById(R.id.moviesRating);
        moveProgressBar = (ProgressBar) itemView.findViewById(R.id.moveProgressBar);
    }

    public ImageView getImgViewMoves() {
        return imgViewMoves;
    }

    public void initData(Context context, Movie movie) {
        setImageWithPicasso(context, movie);
        txtTitle.setText(movie.getMovie());
        txtTagLine.setText(movie.getTagline());
        txtDate.setText("Year: " + movie.getYear());
        txtDuration.setText("Duration: " + movie.getDuration());
        txtDirName.setText("Director: " + movie.getDirector());
        moviesRating.setRating(movie.getRating());
    }

    private void setImageWithPicasso(Context context, Movie movie) {
        moveProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(context).load(movie.getImage()).into(imgViewMoves, new Callback() {
            @Override
            public void onSuccess() {
                if (moveProgressBar != null) {
                    moveProgressBar.setVisibility(View.GONE);
                    imgViewMoves.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onError() {
                moveProgressBar.setVisibility(View.GONE);
                imgViewMoves.setVisibility(View.INVISIBLE);
            }
        });
    }
}
