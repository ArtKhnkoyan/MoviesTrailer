package com.khnkoyan.moviestrailer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.khnkoyan.moviestrailer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesTrailerFragment extends Fragment {
    private WebView wbView;
    private String moviesTitle;
    private ProgressBar progbarwebView;

    public MoviesTrailerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle.containsKey("moviesTitle")) {
            moviesTitle = bundle.getString("moviesTitle");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_trailer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wbView = (WebView) view.findViewById(R.id.webMovies);
        progbarwebView = (ProgressBar) view.findViewById(R.id.progbarwebView);
        progbarwebView.setMax(100);

        wbView.getSettings().setJavaScriptEnabled(true);

        wbView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progbarwebView.setVisibility(View.GONE);
                } else {
                    progbarwebView.setVisibility(View.VISIBLE);
                    progbarwebView.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setSubtitle(title);
            }
        });
        wbView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        loadWebView();
    }

    private void loadWebView() {
        switch (moviesTitle) {
            case "Avengers":
                wbView.loadUrl("https://youtu.be/6ZfuNTqbHE8");
                break;
            case "Interstellar":
                wbView.loadUrl("https://youtu.be/0vxOhd4qlnA");
                break;
            case "Fantastic Four":
                wbView.loadUrl("https://youtu.be/_rRoD28-WgU");
                break;
            case "The Dark Knight":
                wbView.loadUrl("https://youtu.be/EXeTwQWrcwY");
                break;
            case "The Lord of the Rings: The Return of the King":
                wbView.loadUrl("https://youtu.be/r5X-hFf6Bwo");
                break;
            case "Life Is Beautiful":
                wbView.loadUrl("https://youtu.be/dKbrq7dBIJ4");
                break;
            case "Gladiator":
                wbView.loadUrl("https://youtu.be/xButjfhZWVU");
                break;
            case "The Lion King":
                wbView.loadUrl("https://youtu.be/GibiNy4d4gc");
                break;
            case "WALL-E":
                wbView.loadUrl("https://youtu.be/alIq_wG9FNk");
                break;
            case "Saving Private Ryan":
                wbView.loadUrl("https://youtu.be/DSKerypwUDM");
                break;
        }
    }
}