package com.example.samsungproj.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import com.example.samsungproj.R;

public class Mkt extends AppCompatActivity {

    private Button backButton;
    WebView webView;

    public String fileName = "mkt.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkt);
        backButton = findViewById(R.id.backButton);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.a1);
        // init webView
        webView = (WebView) findViewById(R.id.simpleWebView);
        // displaying content in WebView from html file that stored in assets folder
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.
        webView.loadUrl("file:///android_asset/" + fileName);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.start();
                onBackPressed();
            }
        });
    }
}