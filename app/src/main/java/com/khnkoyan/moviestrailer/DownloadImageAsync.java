package com.khnkoyan.moviestrailer;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadImageAsync extends AsyncTask<String, Void, List<Bitmap>> {

    private Context context;
    private ImageView[] imgView;
    private String[] imgUrl;
    private List<Bitmap> downLoadedImage;
    private ProgressDialog progressDialog;

    public DownloadImageAsync(Context context, ImageView... imgView) {
        this.context = context;
        this.imgView = imgView;
        imgUrl = new String[imgView.length];
        downLoadedImage = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = ProgressDialog.show(context, "Loading!", "Please wait...");
    }

    @Override
    protected List<Bitmap> doInBackground(String... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                imgUrl[i] = params[i];
                URL url = new URL(imgUrl[i]);
                InputStream in = url.openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                downLoadedImage.add(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downLoadedImage;
    }

    @Override
    protected void onPostExecute(List<Bitmap> bitmap) {
        super.onPostExecute(bitmap);
        for (int i = 0; i < bitmap.size(); i++) {
            imgView[i].setImageBitmap(bitmap.get(i));
        }
        progressDialog.dismiss();

    }
}
