package com.example.samsungproj.FormulesCalc;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samsungproj.R;

public class A extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText numberaEditText;
    private EditText numberfEditText;
    private EditText numbermEditText;
    private EditText numbervEditText;
    private Spinner spinner;
    private EditText numberv0EditText;
    private EditText numbertEditText;
    private Button ansButton;
    private TextView resultTextView;
    private Button backButton; // Add backButton declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules_a);
        spinner = (Spinner) findViewById(R.id.spinner);
        numberfEditText = findViewById(R.id.numberfEditText);
        numberaEditText = findViewById(R.id.numberaEditText);
        numberv0EditText = findViewById(R.id.numberv0EditText);
        numbertEditText = findViewById(R.id.numbertEditText);
        numbervEditText = findViewById(R.id.numbervEditText);
        numbermEditText = findViewById(R.id.numbermEditText);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.a2);
        ansButton = findViewById(R.id.divideButton);
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton); // Initialize backButton
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.choiseA,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.start();
                calculateResult('s');
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
        EditText AA = (EditText) findViewById(R.id.numberaEditText);
        String A = AA.getText().toString();
        EditText VV = (EditText) findViewById(R.id.numbervEditText);
        String V = VV.getText().toString();
        EditText VV0 = (EditText) findViewById(R.id.numberv0EditText);
        String V0 = VV0.getText().toString();
        EditText TT = (EditText) findViewById(R.id.numbertEditText);
        String T = TT.getText().toString();
        EditText MM = (EditText) findViewById(R.id.numbermEditText);
        String M = MM.getText().toString();
        double numberf;
        double numbera;
        double numberv;
        double numberv0;
        double numbert;
        double numberm;
        if (F.matches("")) {
            numberf = 0.000000000001;
        } else {
            numberf = Double.parseDouble(numberfEditText.getText().toString());
        }
        if (A.matches("")) {
            numbera = 0.000000000001;
        } else {
            numbera = Double.parseDouble(numberaEditText.getText().toString());
        }
        if (V0.matches("")) {
            numberv0 = 0.000000000001;
        } else {
            numberv0 = Double.parseDouble(numberv0EditText.getText().toString());
        }
        if (V.matches("")) {
            numberv = 0.000000000001;
        } else {
            numberv = Double.parseDouble(numbervEditText.getText().toString());
        }
        if (T.matches("")) {
            numbert = 0.000000000001;
        } else {
            numbert = Double.parseDouble(numbertEditText.getText().toString());
        }
        if (M.matches("")) {
            numberm = 0.000000000001;
        } else {
            numberm = Double.parseDouble(numbermEditText.getText().toString());
        }
        double result = 0;
        double result1;
        double result2;

        switch (operator) {
            case 'a':
                if (((numbera == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberm == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberm == 0.000000000001) && (numbera == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numbera != 0.000000000001) && (numberf != 0.000000000001) && (numberm != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numberf == 0.000000000001) {
                        result = numbera * numberm;
                        resultTextView.setText("F = " + String.valueOf(numbera) + " * " + String.valueOf(numberm) +
                                " = " + String.valueOf(result) + " H");
                    } else if (numbera == 0.000000000001) {
                        if (numberm != 0) {
                            result = numberf / numberm;
                            resultTextView.setText("a = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberm) + " = " + String.valueOf(result) + " м/с^2");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberm == 0.000000000001) {
                        if (numbera != 0) {
                            result = numberf / numbera;
                            resultTextView.setText("m = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numbera) + " = " + String.valueOf(result) + " кг");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    }
                }
                break;
            case 't':
                if (((numberv == 0.000000000001) && (numberv0 == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numbera == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numbert == 0.000000000001)) ||
                        ((numberv0 == 0.000000000001) && (numbera == 0.000000000001)) ||
                        ((numberv0 == 0.000000000001) && (numbert == 0.000000000001)) ||
                        ((numbera == 0.000000000001) && (numbert == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numbera != 0.000000000001) && (numbert != 0.000000000001) && (numberv != 0.000000000001) && (numberv0 != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numbera == 0.000000000001) {
                        if (numbert != 0) {
                            result = (numberv - numberv0) / numbert;
                            resultTextView.setText("a = (" + String.valueOf(numberv) + " - " + String.valueOf(numberv0) +
                                    ") / " + String.valueOf(numbert) +
                                    " = " + String.valueOf(result) + " м/с^2");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0.000000000001) {
                        result = numbera * numbert + numberv0;
                        resultTextView.setText("V = " + String.valueOf(numbera) + " * " + String.valueOf(numbert) + " + "
                                + String.valueOf(numberv0) + " = " + String.valueOf(result) + " м/с");

                    } else if (numberv0 == 0.000000000001) {
                        result = -1 * numbera * numbert + numberv;
                        resultTextView.setText("V0 = " + String.valueOf(numberv) + " - " + String.valueOf(numbera) + " * "
                                + String.valueOf(numbert) + " = " + String.valueOf(result) + " м/с");

                    } else if (numbert == 0.000000000001) {
                        if (numbera != 0) {
                            result = (numberv - numberv0) / numbera;
                            resultTextView.setText("t = (" + String.valueOf(numberv) + " - " + String.valueOf(numberv0) +
                                    ") / " + String.valueOf(numbera) +
                                    " = " + String.valueOf(result) + " с");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    }
                }
                break;
            case 'r':
                if (((numbera == 0.000000000001) && (numberm == 0.000000000001)) ||
                        ((numbera == 0.000000000001) && (numberv == 0.000000000001)) ||
                        ((numberm == 0.000000000001) && (numberv == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numbera != 0.000000000001) && (numberv != 0.000000000001) && (numberm != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numbera == 0.000000000001) {
                        if (numberm != 0) {
                            result = numberv * numberv / numberm;
                            resultTextView.setText("a = " + String.valueOf(numberv) + " ^ 2 " + " / " +
                                    String.valueOf(numberm) + " = " + String.valueOf(result) + " м/c^2");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0.000000000001) {
                        if (numbera >= 0 && numberm >= 0) {
                            double a = numbera * numberm;
                            result = Math.sqrt(a);
                            resultTextView.setText("V = sqrt(" + String.valueOf(numbera) + " * " + String.valueOf(numberm) + ") = " + String.valueOf(result) + " м/с");
                        } else {
                            resultTextView.setText("Ошибка: корень из отрицательного числа");
                        }
                    } else if (numberm == 0.000000000001) {
                        if (numbera != 0) {
                            result = numberv * numberv / numbera;
                            resultTextView.setText("R = " + String.valueOf(numberv) + " ^ 2 " + " / " +
                                    String.valueOf(numbera) + " = " + String.valueOf(result) + " м");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView;
        numberfEditText = findViewById(R.id.numberfEditText);
        numberaEditText = findViewById(R.id.numberaEditText);
        numbervEditText = findViewById(R.id.numbervEditText);
        numberv0EditText = findViewById(R.id.numberv0EditText);
        numbermEditText = findViewById(R.id.numbermEditText);
        numbertEditText = findViewById(R.id.numbertEditText);
        ansButton = findViewById(R.id.divideButton);
        resultTextView = findViewById(R.id.resultTextView);
        ansButton.setVisibility(View.GONE);
        switch (position) {
            case 0:
                textView = findViewById(R.id.resultTextView);
                textView.setText("F = a * m\nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numberaEditText.setText("");
                numberv0EditText.setText("");
                numbermEditText.setText("");
                numbertEditText.setText("");
                numberv0EditText.setVisibility(View.GONE);
                numbervEditText.setVisibility(View.GONE);
                numbertEditText.setVisibility(View.GONE);
                numberfEditText.setVisibility(View.VISIBLE);
                numberaEditText.setVisibility(View.VISIBLE);
                numbermEditText.setVisibility(View.VISIBLE);
                numberfEditText.setHint("Введите F (H)");
                numberaEditText.setHint("Введите a (м/с^2)");
                numbermEditText.setHint("Введите m (кг)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('a');
                    }
                });
                break;
            case 1:
                textView = findViewById(R.id.resultTextView);
                textView.setText("a = (V - V0) / t\nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numberaEditText.setText("");
                numberv0EditText.setText("");
                numbermEditText.setText("");
                numbertEditText.setText("");
                numberv0EditText.setVisibility(View.VISIBLE);
                numbervEditText.setVisibility(View.VISIBLE);
                numbertEditText.setVisibility(View.VISIBLE);
                numberfEditText.setVisibility(View.GONE);
                numberaEditText.setVisibility(View.VISIBLE);
                numbermEditText.setVisibility(View.GONE);
                numbervEditText.setHint("Введите V (м)");
                numberv0EditText.setHint("Введите V0 (м/с)");
                numbertEditText.setHint("Введите t (с)");
                numberaEditText.setHint("Введите a (м/с^2)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('t');
                    }
                });
                break;
            case 2:
                textView = findViewById(R.id.resultTextView);
                textView.setText("a = V^2 / R \nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numberaEditText.setText("");
                numberv0EditText.setText("");
                numbermEditText.setText("");
                numbertEditText.setText("");
                numberv0EditText.setVisibility(View.GONE);
                numberfEditText.setVisibility(View.GONE);
                numbertEditText.setVisibility(View.GONE);
                numbervEditText.setVisibility(View.VISIBLE);
                numberaEditText.setVisibility(View.VISIBLE);
                numbermEditText.setVisibility(View.VISIBLE);
                numbervEditText.setHint("Введите V (м/c)");
                numberaEditText.setHint("Введите a центростремительное (м/с^2)");
                numbermEditText.setHint("Введите R (м)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('r');
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

