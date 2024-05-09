/*
package com.example.samsungproj.FormulesCalc;
import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.samsungproj.R;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
                calculateResult('s');
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Go back to the previous page
            }
        });

    }

    private void calculateResult(char operator) {
        EditText FF = (EditText) findViewById(R.id.numberfEditText);
        String F = FF.getText().toString();
        EditText AA = (EditText) findViewById(R.id.numbergEditText);
        String A = AA.getText().toString();
        EditText VV = (EditText) findViewById(R.id.numberpEditText);
        String V = VV.getText().toString();
        EditText VV0 = (EditText) findViewById(R.id.numbervEditText);
        String V0 = VV0.getText().toString();
        EditText TT = (EditText) findViewById(R.id.numberx0EditText);
        String T = TT.getText().toString();
        EditText MM = (EditText) findViewById(R.id.numberx0EditText);
        String M = MM.getText().toString();
        double numberf;
        double numbera;
        double numberv;
        double numberv0;
        double numbert;
        double numberm;
        if (F.matches("")) {
            numberf = 0.000000000001;
        }else {
            numberf = Double.parseDouble(numberfEditText.getText().toString());
        }
        if (A.matches("")) {
            numbera = 0.000000000001;
        }else {
            numbera = Double.parseDouble(numberaEditText.getText().toString());
        }
        if (V0.matches("")) {
            numberv0 = 0.000000000001;
        }else {
            numberv0 = Double.parseDouble(numberv0EditText.getText().toString());
        }
        if (V.matches("")) {
            numberv = 0.000000000001;
        }else {
            numberv = Double.parseDouble(numbervEditText.getText().toString());
        }
        if (T.matches("")) {
            numbert = 0.000000000001;
        }else {
            numbert = Double.parseDouble(numbertEditText.getText().toString());
        }
        if (M.matches("")) {
            numberm = 0.000000000001;
        }else {
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
                        if (numberm != 0 ) {
                            result = numberf / numberm;
                            resultTextView.setText("a = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberm) + " = " + String.valueOf(result) + " м/с^2");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberm == 0.000000000001) {
                        if (numbera != 0 ) {
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
                        }else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0.000000000001) {
                        result = numbera * numbert + numberv0;
                        resultTextView.setText("V = " + String.valueOf(numbera) + " * " + String.valueOf(numbert) + " + "
                                    + String.valueOf(numberv0) + " = " + String.valueOf(result) + " м/с");

                    } else if (numberv0 == 0.000000000001) {
                        result = -1 * numbera * numbert + numberv;
                        resultTextView.setText("V0 = " + String.valueOf(numberv) + " - " + String.valueOf(numbera) + " + "
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
            case 'S':
                if (((numberp == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberp == 0.000000000001)) ||
                        ((numberg == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberg == 0.000000000001)) ||
                        ((numberg == 0.000000000001) && (numberp == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numberp != 0.000000000001) && (numberf != 0.000000000001) && (numberv != 0.000000000001) && (numberg != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numberf == 0.000000000001) {
                        result = numberp * numberv + (numberg * numberp * numberp) / 2;
                        resultTextView.setText("S = " + String.valueOf(numberp) + " * " + String.valueOf(numberv) +
                                " + (" + String.valueOf(numberp) + "^2 * " + String.valueOf(numberg) + ") / 2" +
                                " = " + String.valueOf(result) + " м");
                    } else if (numberp == 0.000000000001) { // время/////////////////////////////////
                        if (numberg == 0) {
                            if (numberv != 0) {
                                result = numberf / numberv;
                                resultTextView.setText("t = " + String.valueOf(result) + " c");
                            } else {
                                resultTextView.setText("Ошибка: деление на 0");
                            }
                        } else {
                            double D = numberv * numberv - 4 * (numberg / 2) * (-1 * numberf);
                            if (D < 0) {
                                resultTextView.setText("Ошибка: Дескриминант меньше 0");
                            } else {
                                result1 = (Math.sqrt(D) + numberv * (-1)) / (2 * (numberg / 2));
                                result2 = (Math.sqrt(D) * (-1) + numberv * (-1)) / (2 * (numberg / 2));
                                resultTextView.setText("t1 = " + String.valueOf(result1) + "\n" +
                                        "t2 = " + String.valueOf(result2) + " c");
                            }
                        }
                    } else if (numberv == 0.000000000001) {
                        if (numberp != 0 && numberp != 0.000000000001) {
                            double a = numberf - (numberg * numberp * numberp) / 2;
                            result = a / numberp;
                            resultTextView.setText("v0 = " + String.valueOf(result) + " м/с");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberg == 0.000000000001) {
                        result = (numberf - numberv * numberp) * 2 / numberp / numberp;
                        resultTextView.setText("a = " + String.valueOf(result) + " м/с^2");
                    }
                }
                break;
            case 'X':
                if (((numberp == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberp == 0.000000000001)) ||
                        ((numberg == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberg == 0.000000000001)) ||
                        ((numberg == 0.000000000001) && (numberp == 0.000000000001)) ||
                        ((numberp == 0.000000000001) && (numberx0 == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberx0 == 0.000000000001)) ||
                        ((numberf == 0.000000000001) && (numberx0 == 0.000000000001)) ||
                        ((numberg == 0.000000000001) && (numberx0 == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numberp != 0.000000000001) && (numberf != 0.000000000001) && (numberv != 0.000000000001) && (numberg != 0.000000000001) && (numberx0 != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numberf == 0.000000000001) {
                        result = numberx0 + numberp * numberv + (numberg * numberp * numberp) / 2;
                        resultTextView.setText("S = " + String.valueOf(numberx0) + " + " + String.valueOf(numberp) + " * " + String.valueOf(numberv) +
                                " + (" + String.valueOf(numberp) + "^2 * " + String.valueOf(numberg) + ") / 2" +
                                " = " + String.valueOf(result) + " м");
                    } else if (numberp == 0.000000000001) { // время/////////////////////////////////
                        if (numberg == 0) {
                            if (numberv != 0) {
                                result = (numberf - numberx0) / numberv;
                                resultTextView.setText("t = " + String.valueOf(result) + " c");
                            } else {
                                resultTextView.setText("Ошибка: деление на 0");
                            }
                        } else {
                            double D = numberv * numberv - 4 * (numberg / 2) * (-1 * numberf + numberx0);
                            if (D < 0) {
                                resultTextView.setText("Ошибка: Дескриминант меньше 0");
                            } else {
                                result1 = (Math.sqrt(D) + numberv * (-1)) / (2 * (numberg / 2));
                                result2 = (Math.sqrt(D) * (-1) + numberv * (-1)) / (2 * (numberg / 2));
                                resultTextView.setText("t1 = " + String.valueOf(result1) + "\n" +
                                        "t2 = " + String.valueOf(result2) + " c");
                            }
                        }
                    } else if (numberv == 0.000000000001) {
                        if (numberp != 0 && numberp != 0.000000000001) {
                            double a = numberf - numberx0 - (numberg * numberp * numberp) / 2 ;
                            result = a / numberp;
                            resultTextView.setText("v0 = " + String.valueOf(result) + " м/с");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberg == 0.000000000001) {
                        result = (numberf - numberx0 - numberv * numberp) * 2 / numberp / numberp;
                        resultTextView.setText("a = " + String.valueOf(result) + " м/с^2");
                    }else if (numberx0 == 0.000000000001) {
                        result = numberf - (numberp * numberv + (numberg * numberp * numberp) / 2);
                        resultTextView.setText("a = " + String.valueOf(result) + " м");
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
                textView.setText("S = v * t\nВведите все известные значения\n");
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
                textView.setText("x = x0 + v * t\nВведите все известные значения\n");
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
                textView.setText("S = v0 * t + (a * t^2) / 2 \nВведите все известные значения\n");
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
                numberfEditText.setHint("Введите F (м)");
                numberaEditText.setHint("Введите a (м/с)");
                numbermEditText.setHint("Введите m (с)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('S');
                    }
                });
                break;
            case 3:
                textView = findViewById(R.id.resultTextView);
                textView.setText("x = x0 + v0 * t + (a * t^2) / 2 \nВведите все известные значения\n");
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
                numberfEditText.setHint("Введите F (м)");
                numberaEditText.setHint("Введите a (м/с)");
                numbermEditText.setHint("Введите m (с)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('X');
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

*/
