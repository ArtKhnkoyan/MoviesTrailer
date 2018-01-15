package com.khnkoyan.moviestrailer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.khnkoyan.moviestrailer.R;
import com.khnkoyan.moviestrailer.activities.MoviesDetail;
import com.khnkoyan.moviestrailer.models.Movie;
import com.khnkoyan.moviestrailer.viewHolders.MoviesViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
    private Context context;
    private List<Movie> movieList;

    public MoviesAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_list_layout, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        final Movie movie = getItem(position);
        holder.initData(context, movie);
        holder.getImgViewMoves().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoviesDetail.class);
                intent.putExtra("movie", new Gson().toJson(movie));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            movieList = new ArrayList<>();
        }
        return movieList.size();
    }

    private Movie getItem(int position) {
        if (movieList != null && position >= 0 && position < movieList.size()) {
            return movieList.get(position);
        }
        return null;
    }
}
