package com.example.samsungproj.themes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungproj.R;

public class Kinematika extends AppCompatActivity {

    private Button backButton;
    WebView webView;

    public String fileName = "myfile.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinematika);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.a1);
        backButton = findViewById(R.id.backButton);
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