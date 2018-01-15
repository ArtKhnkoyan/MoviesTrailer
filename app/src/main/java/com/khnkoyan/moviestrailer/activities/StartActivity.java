package com.khnkoyan.moviestrailer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.khnkoyan.moviestrailer.DownloadImageAsync;
import com.khnkoyan.moviestrailer.R;

public class StartActivity extends AppCompatActivity {
    private ImageView imgLoad_1;
    private ImageView imgLoad_2;
    private static final String[] IMAGE_URL = {"http://laoblogger.com/images/icon-networking-7.jpg",
            "http://livecorals.ru/image/data/welcome3.png"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imgLoad_1 = (ImageView) findViewById(R.id.imgLoad_1);
        imgLoad_2 = (ImageView) findViewById(R.id.imgLoad_2);
    }

    private void toMovieInfoActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MoviesInfo.class);
                startActivity(intent);
            }
        }, 5000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (haveNetworkConnection()) {
            DownloadImageAsync downloadImageAsync = new DownloadImageAsync(this, imgLoad_1, imgLoad_2);
            downloadImageAsync.execute(IMAGE_URL);
            toMovieInfoActivity();
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            // Setting Dialog Title
            alertDialog.setTitle("Confirm...");
            // Setting Dialog Message
            alertDialog.setMessage("Do you want to go to wifi settings?");
            alertDialog.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Activity transfer to wifi settings
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("no",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.cancel();
                        }
                    }).show();
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equals("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equals("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
