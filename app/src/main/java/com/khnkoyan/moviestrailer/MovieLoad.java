package com.khnkoyan.moviestrailer;

import android.content.Context;

import com.google.gson.Gson;
import com.khnkoyan.moviestrailer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieLoad {
    private Context context;

    public MovieLoad() {
    }

    public MovieLoad(Context context) {
        this.context = context;
    }

    private String getJsonForMovies(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void getJsonForMovies(String url, final List<Movie> movieList) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               // Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    JSONObject jsonBody = new JSONObject(result);
                    parseItem(movieList, jsonBody);
                } catch (JSONException joe) {
                    joe.printStackTrace();
                }
            }
        });
    }

    public List<Movie> getMoviesList() {
        List<Movie> movieList = new ArrayList<>();
        try {
            String url = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";
            String result = getJsonForMovies(url);
            JSONObject jsonBody = new JSONObject(result);
            parseItem(movieList, jsonBody);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (JSONException joe) {
            System.out.println(joe.getMessage());
        }
        return movieList;
    }

    public List<Movie> getMoviesListAsynch() {
        List<Movie> movieList = new ArrayList<>();
        String url = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";
        getJsonForMovies(url, movieList);
        return movieList;
    }

    private void parseItem(List<Movie> movieList, JSONObject jsonBody) throws IOException, JSONException {
        JSONArray moviesJsonArray = jsonBody.getJSONArray("movies");
        Gson gson = new Gson();
        for (int i = 0; i < moviesJsonArray.length(); i++) {
            JSONObject jsonObj = moviesJsonArray.getJSONObject(i);
            Movie item = gson.fromJson(jsonObj.toString(), Movie.class);
//            Movie item = new Movie();
//            item.setImage(jsonObj.getString("image"));
//            item.setMovie(jsonObj.getString("movie"));
//            item.setTagline(jsonObj.getString("tagline"));
//            item.setYear(jsonObj.getInt("year"));
//            item.setDuration(jsonObj.getString("duration"));
//            item.setDirector(jsonObj.getString("director"));
//            item.setRating((float) jsonObj.getDouble("rating"));

//            List<Movie.Cast> castList = new ArrayList<>();
//            for (int j = 0; j < castList.size(); j++) {
//                Movie.Cast castItem = new Movie.Cast();
//                castItem.setName(jsonObj.getJSONArray("cast").getJSONObject(j).getString("name"));
//                movieList.add(item);
//            }
            movieList.add(item);
        }
    }

}

