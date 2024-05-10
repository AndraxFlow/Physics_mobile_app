package com.example.samsungproj;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.samsungproj.FormulesCalc.FArch;

public class Themes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        int a;
        // В активности получателе
        String receivedValue = getIntent().getStringExtra("ключ");
        switch (receivedValue){
            case "Кинематика":
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                 a = 1;
            break;

            case "Динамика" :
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                 a = 0;
            break;
        }
    }

}