package com.example.samsungproj.FormulesCalc;
import android.app.Person;
import android.content.Intent;
import android.media.MediaPlayer;
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

public class SorX extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private EditText numberfEditText;
    private EditText numbergEditText;
    private EditText numberpEditText;
    private EditText numbervEditText;
    private Spinner spinner;
    private EditText numberx0EditText;

    private Button ansButton;
    private TextView resultTextView;
    private Button backButton; // Add backButton declaration


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules_sor_x);

        spinner = (Spinner) findViewById(R.id.spinner);

        numberfEditText = findViewById(R.id.numberfEditText);
        numbergEditText = findViewById(R.id.numbergEditText);
        numberpEditText = findViewById(R.id.numberpEditText);
        numbervEditText = findViewById(R.id.numbervEditText);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.a2);

        ansButton = findViewById(R.id.divideButton);
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton); // Initialize backButton




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.choise,
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
        EditText GG = (EditText) findViewById(R.id.numbergEditText);
        String G = GG.getText().toString();
        EditText PP = (EditText) findViewById(R.id.numberpEditText);
        String P = PP.getText().toString();
        EditText VV = (EditText) findViewById(R.id.numbervEditText);
        String V = VV.getText().toString();
        EditText X0 = (EditText) findViewById(R.id.numberx0EditText);
        String X = X0.getText().toString();
        double numberf;
        double numberg;
        double numberp;
        double numberv;
        double numberx0;
        if (F.matches("")) {
            numberf = 0.000000000001;
        }else {
            numberf = Double.parseDouble(numberfEditText.getText().toString());
        }
        if (G.matches("")) {
            numberg = 0.000000000001;
        }else {
            numberg = Double.parseDouble(numbergEditText.getText().toString());
        }
        if (P.matches("")) {
            numberp = 0.000000000001;
        }else {
            numberp = Double.parseDouble(numberpEditText.getText().toString());
        }
        if (V.matches("")) {
            numberv = 0.000000000001;
        }else {
            numberv = Double.parseDouble(numbervEditText.getText().toString());
        }
        if (X.matches("")) {
            numberx0 = 0.000000000001;
        }else {
            numberx0 = Double.parseDouble(numberx0EditText.getText().toString());
        }
        double result = 0;
        double result1;
        double result2;

        switch (operator) {
            case 's':
                if (((numberp == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberp == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numberp != 0.000000000001) && (numberf != 0.000000000001) && (numberv != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numberf == 0.000000000001) {
                        result = numberp * numberv;
                        resultTextView.setText("S = " + String.valueOf(numberp) + " * " + String.valueOf(numberv) +
                                " = " + String.valueOf(result));
                    } else if (numberp == 0.000000000001) {
                        if (numberv != 0 && numberg != 0) {
                            result = numberf / numberv;
                            resultTextView.setText("p = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberv) + " = " + String.valueOf(result));
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0.000000000001) {
                        if (numberp != 0 && numberg != 0) {
                            result = numberf / numberp;
                            resultTextView.setText("v = " + String.valueOf(numberf) + " / "
                                    + String.valueOf(numberp) + " = " + String.valueOf(result));
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    }
                }
                break;
            case 'x':
                if (((numberp == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberp == 0.000000000001)) ||
                        ((numberx0 == 0.000000000001) && (numberf == 0.000000000001)) ||
                        ((numberv == 0.000000000001) && (numberx0 == 0.000000000001)) ||
                        ((numberx0 == 0.000000000001) && (numberp == 0.000000000001))) {
                    resultTextView.setText("Ошибка: Слишком много неизвестных");
                } else if ((numberp != 0.000000000001) && (numberf != 0.000000000001) && (numberv != 0.000000000001) && (numberx0 != 0.000000000001)) {
                    resultTextView.setText("Ошибка: Вы уже все знаете");
                } else {
                    if (numberf == 0.000000000001) {
                        result = numberp * numberv + numberx0;
                        resultTextView.setText("x = " + String.valueOf(numberp) + " * " + String.valueOf(numberv) +
                                " + " + String.valueOf(numberx0) +
                                " = " + String.valueOf(result) + " м");
                    } else if (numberp == 0.000000000001) {
                        if (numberv != 0 && numberv != 0.000000000001) {
                            result = (numberf - numberx0) / numberv;
                            resultTextView.setText("t = (" + String.valueOf(numberf) + " - " + String.valueOf(numberx0) + ") / "
                                    + String.valueOf(numberv) + " = " + String.valueOf(result) + " с");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberv == 0.000000000001) {
                        if (numberp != 0 && numberp != 0.000000000001) {
                            result = (numberf - numberx0) / numberp;
                            resultTextView.setText("v = (" + String.valueOf(numberf) + " - " + String.valueOf(numberx0) + ") / "
                                    + String.valueOf(numberp) + " = " + String.valueOf(result) + " м/с");
                        } else {
                            resultTextView.setText("Ошибка: деление на 0");
                        }
                    } else if (numberx0 == 0.000000000001) {
                        result = numberf - numberv * numberp;
                        resultTextView.setText("x0 = " + String.valueOf(numberf) + " - "
                                + String.valueOf(numberp) + " * " + String.valueOf(numberv)
                                + " = " + String.valueOf(result) + " м/с");
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
        numbergEditText = findViewById(R.id.numbergEditText);
        numberpEditText = findViewById(R.id.numberpEditText);
        numbervEditText = findViewById(R.id.numbervEditText);
        numberx0EditText = findViewById(R.id.numberx0EditText);
        ansButton = findViewById(R.id.divideButton);
        resultTextView = findViewById(R.id.resultTextView);
        ansButton.setVisibility(View.GONE);






        switch (position) {
            case 0:
                textView = findViewById(R.id.resultTextView);
                textView.setText("S = v * t\nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numbergEditText.setText("");
                numberx0EditText.setText("");
                numberpEditText.setText("");
                numberfEditText.setHint("Введите S (м)");
                numbergEditText.setVisibility(View.GONE);
                numberx0EditText.setVisibility(View.GONE);
                numbervEditText.setHint("Введите v (м/с)");
                numberpEditText.setHint("Введите t (с)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('s');
                    }
                });
                break;
            case 1:
                textView = findViewById(R.id.resultTextView);
                textView.setText("x = x0 + v * t\nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numbergEditText.setText("");
                numberx0EditText.setText("");
                numberpEditText.setText("");
                numberfEditText.setHint("Введите x (м)");
                numbervEditText.setVisibility(View.VISIBLE);
                numberx0EditText.setVisibility(View.VISIBLE);
                numbergEditText.setVisibility(View.GONE);
                numberx0EditText.setHint("Введите x0 (м)");
                numbervEditText.setHint("Введите v (м/с)");
                numberpEditText.setHint("Введите t (с)");
                ansButton.setVisibility(View.VISIBLE);
                ansButton.setText("Рассчитать");
                ansButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calculateResult('x');
                    }
                });
                break;
            case 2:
                textView = findViewById(R.id.resultTextView);
                textView.setText("S = v0 * t + (a * t^2) / 2 \nВведите все известные значения\n");
                numbervEditText.setText("");
                numberfEditText.setText("");
                numbergEditText.setText("");
                numberx0EditText.setText("");
                numberpEditText.setText("");
                numberfEditText.setHint("Введите S (м)");
                numbervEditText.setVisibility(View.VISIBLE);
                numberx0EditText.setVisibility(View.GONE);
                numbergEditText.setVisibility(View.VISIBLE);
                numbervEditText.setHint("Введите v0 (м/с)");
                numbergEditText.setHint("Введите a (м/(с^2))");
                numberpEditText.setHint("Введите t (с)");
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
                numbergEditText.setText("");
                numberx0EditText.setText("");
                numberpEditText.setText("");
                numberfEditText.setHint("Введите x (м)");
                numbervEditText.setVisibility(View.VISIBLE);
                numberx0EditText.setVisibility(View.VISIBLE);
                numberx0EditText.setHint("Введите x0 (м)");
                numbervEditText.setHint("Введите v0 (м/с)");
                numbergEditText.setHint("Введите a (м/(с^2))");
                numberpEditText.setHint("Введите t (с)");
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

