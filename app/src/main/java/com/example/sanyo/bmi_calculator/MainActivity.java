package com.example.sanyo.bmi_calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int age, feet, height;
    double weight, new_weight, bmi;
    Button calculate;
    EditText editTextAge;
    EditText editTextWeight;
    EditText editTextFeet;
    EditText editTextInches;
    TextView normal;
    TextView range;
    TextView advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextFeet = (EditText) findViewById(R.id.editTextFeet);
        editTextInches = (EditText) findViewById(R.id.editTextInches);
        calculate = findViewById(R.id.buttonCalculate);
        normal = findViewById(R.id.textViewBMI);
        range = findViewById(R.id.textViewBMIRange);
        advice = findViewById(R.id.textViewAdvice);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    age = Integer.parseInt(editTextAge.getText().toString());
                    if (age < 18) {
                        Toast.makeText(getApplicationContext(), "Age should be greater than 18", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            weight = Double.parseDouble(editTextWeight.getText().toString());
                            try {
                                feet = Integer.parseInt(editTextFeet.getText().toString());
                                try {
                                    height = (feet * 12) + Integer.parseInt(editTextInches.getText().toString());
                                    bmi = Math.round((703 * weight) / Math.pow(height, 2));
                                    Log.d("demo","if statement"+bmi);
                                    TextView result = findViewById(R.id.textViewResult);
                                    result.setText("Result");
                                    if (bmi < 18.5) {
                                        Log.d("demo","if statement");
                                        String styledText = "BMI = " + bmi + " <font color='pink'>(Underweight)</font>.";
                                        normal.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                        range.setText("Normal BMI Range: 18.5 - 25");
                                        new_weight = Math.round(((18.5 * Math.pow(height, 2)) / 703) - weight);
                                        advice.setText("You need to gain " + new_weight + " to reach a BMI of 25");

                                    } else if (bmi < 25) {
                                        Log.d("demo","if statement");
                                        String styledText = "BMI = " + bmi + " <font color='green'>(Normal)</font>.";
                                        normal.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                        range.setText("Normal BMI Range: 18.5 - 25");
                                        advice.setText("Keep up the Good Work!");

                                    } else if (bmi < 30) {
                                        Log.d("demo","if statement");
                                        String styledText = "BMI = " + bmi + " <font color='yellow'>(Overweight)</font>.";
                                        normal.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                        range.setText("Normal BMI Range: 18.5 - 25");
                                        new_weight = Math.round(weight - ((25 * Math.pow(height, 2)) / 703));
                                        advice.setText("You need to lose " + new_weight + " to reach a BMI of 25");

                                    } else if (bmi >= 30) {
                                        Log.d("demo","if statement");
                                        String styledText = "BMI = " + bmi + " <font color='red'>(Obese)</font>.";
                                        normal.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                        range.setText("Normal BMI Range: 18.5 - 25");
                                        new_weight = Math.round(weight - ((25 * Math.pow(height, 2)) / 703));
                                        advice.setText("You need to lose " + new_weight + " to reach a BMI of 25");
                                    }

                                } catch (NumberFormatException e) {
                                    Toast.makeText(getApplicationContext(), "Inches Field is Required!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Feet field is Required!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Weight field is Required!", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Age field is Required!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
