package com.example.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private String username;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = findViewById(R.id.txtInput);
        outputDisplay = findViewById(R.id.txtSolution);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        fileName = username + "_calculation_history.txt";

        Button buttonLogout = findViewById(R.id.btnLogout);
        Button buttonHistory = findViewById(R.id.btnHistory);
        Button button0 = findViewById(R.id.btnZero);
        Button button1 = findViewById(R.id.btnOne);
        Button button2 = findViewById(R.id.btnTwo);
        Button button3 = findViewById(R.id.btnThree);
        Button button4 = findViewById(R.id.btnFour);
        Button button5 = findViewById(R.id.btnFive);
        Button button6 = findViewById(R.id.btnSix);
        Button button7 = findViewById(R.id.btnSeven);
        Button button8 = findViewById(R.id.btnEight);
        Button button9 = findViewById(R.id.btnNine);

        Button buttonAdd = findViewById(R.id.btnAdd);
        Button buttonSub = findViewById(R.id.btnSubtract);
        Button buttonDivide = findViewById(R.id.btnDivide);
        Button buttonDot = findViewById(R.id.btnDecimal);
        Button buttonMultiply = findViewById(R.id.btnMultiply);
        Button buttonClear = findViewById(R.id.btnClear);
        Button buttonEqual = findViewById(R.id.btnEquals);
        Button buttonBS = findViewById(R.id.btnBack);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(CalculatorActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(CalculatorActivity.this, HistoryActivity.class);
                historyIntent.putExtra("USERNAME", username);
                startActivity(historyIntent);
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "9");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCalculations();
                currentSymbol = ADDITION;
                outputDisplay.setText(decimalFormat.format(firstValue) + "+");
                inputDisplay.setText(null);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCalculations();
                currentSymbol = SUBTRACTION;
                outputDisplay.setText(decimalFormat.format(firstValue) + "-");
                inputDisplay.setText(null);
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCalculations();
                currentSymbol = MULTIPLICATION;
                outputDisplay.setText(decimalFormat.format(firstValue) + "x");
                inputDisplay.setText(null);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCalculations();
                currentSymbol = DIVISION;
                outputDisplay.setText(decimalFormat.format(firstValue) + "/");
                inputDisplay.setText(null);
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + ".");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputDisplay.getText().length() > 0) {
                    CharSequence currentText = inputDisplay.getText();
                    inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    firstValue = Double.NaN;
                    secondValue = Double.NaN;
                    inputDisplay.setText("");
                    outputDisplay.setText("");
                }
            }
        });
        buttonBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputDisplay.getText().length() > 0) {
                    CharSequence currentText = inputDisplay.getText();
                    inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
                }
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCalculations();
                outputDisplay.setText(decimalFormat.format(firstValue));
                String calculation = outputDisplay.getText().toString();
                saveCalculationToHistory(calculation);
                firstValue = Double.parseDouble(outputDisplay.getText().toString());
                currentSymbol = '0';
            }
        });
    }

    private void allCalculations() {
        if (!Double.isNaN(firstValue)) {
            if (inputDisplay.getText().length() == 0) return;
            secondValue = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);
            if (currentSymbol == ADDITION) firstValue = this.firstValue + secondValue;
            else if (currentSymbol == SUBTRACTION) firstValue = this.firstValue - secondValue;
            else if (currentSymbol == MULTIPLICATION) firstValue = this.firstValue * secondValue;
            else if (currentSymbol == DIVISION) firstValue = this.firstValue / secondValue;
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
            }
        }
    }

    private void saveCalculationToHistory(String calculation) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_APPEND);
            fos.write((calculation + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
