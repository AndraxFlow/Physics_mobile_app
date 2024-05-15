package com.example.samsungproj.FormulesCalc;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samsungproj.R;

public class FArch extends AppCompatActivity {

    private EditText numberfEditText;
    private EditText numbergEditText;
    private EditText numberpEditText;
    private EditText numbervEditText;


    private Button ansButton;
    private TextView resultTextView;
    private Button backButton; // Add backButton declaration



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules_farch);
        numberfEditText = findViewById(R.id.numberfEditText);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.a2);

        numbergEditText = findViewById(R.id.numbergEditText);
        numberpEditText = findViewById(R.id.numberpEditText);
        numbervEditText = findViewById(R.id.numbervEditText);

        ansButton = findViewById(R.id.divideButton);
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton); // Initialize backButton

        ansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.start();
                calculateResult('*');
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.start();
                onBackPressed(); // Go back to the previous page
            }
        });

    }

    private void calculateResult(char operator) {
        EditText FF = (EditText) findViewById(R.id.numberfEditText);
        String F = FF.getText().toString();
        EditText GG = (EditText) findViewById(R.id.numbergEditText);
        String G = GG.getText().toString();
        EditText PP = (EditText) findViewById(R.id.numberpEditText);
        String P = PP.getText().toString();
        EditText VV = (EditText) findViewById(R.id.numbervEditText);
        String V = VV.getText().toString();
        double numberf;
        double numberg;
        double numberp;
        double numberv;
        if (F.matches("")) {
             numberf = 0;
        }else {
             numberf = Double.parseDouble(numberfEditText.getText().toString());
        }
        if (G.matches("")) {
             numberg = 9.8;
        }else {
             numberg = Double.parseDouble(numbergEditText.getText().toString());
        }
        if (P.matches("")) {
             numberp = 0;
        }else {
             numberp = Double.parseDouble(numberpEditText.getText().toString());
        }
        if (V.matches("")) {
             numberv = 0;
        }else {
             numberv = Double.parseDouble(numbervEditText.getText().toString());
        }
        double result = 0;

        switch (operator) {
            case '*':
                if (((numberp == 0) && (numberf == 0)) ||
                        ((numberv == 0) && (numberf == 0)) ||
                        ((numberv == 0) && (numberp == 0))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else {
                    if (numberf == 0) {
                        result = numberp * numberv * numberg;
                        resultTextView.setText("F = " + String.valueOf(numberp) + " * " + String.valueOf(numberv) +
                                " * " + String.valueOf(numberg) + " = " + String.valueOf(result));
                    } else if (numberp == 0) {
                        if (numberv != 0 && numberg != 0) {
                            result = numberf / numberg / numberv;
                            resultTextView.setText("p = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberv) +
                                    " / " + String.valueOf(numberg) + " = " + String.valueOf(result));
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0) {
                        if (numberp != 0 && numberg != 0) {
                            result = numberf / numberg / numberp;
                            resultTextView.setText("v = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberp) +
                                    " / " + String.valueOf(numberg) + " = " + String.valueOf(result));
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    }
                    break;
                }
        }


    }
}

