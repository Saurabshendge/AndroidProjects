package com.example.practiceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    EditText height, weight;;
    Button calc;
    TextView result, value;
    LinearLayout status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        height = findViewById(R.id.edt_txt1);
        weight = findViewById(R.id.edt_txt2);
        calc = findViewById(R.id.calc_btn);
        result = findViewById(R.id.txtview);
        value = findViewById(R.id.txtView1);
        status = findViewById(R.id.indicator);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double ht = Double.parseDouble(height.getText().toString());
                double wt = Double.parseDouble(weight.getText().toString());

                double bmi = wt / (ht * ht);
                double val = Math.round(bmi*100.0)/100.0;

                if (bmi > 24.9) {
                    result.setText("You are over Weight");
                    status.setBackgroundColor(getResources().getColor(R.color.OW, getTheme()));
                } else if (bmi < 18.0) {
                    result.setText("You are underweight");
                    status.setBackgroundColor(getResources().getColor(R.color.UW, getTheme()));
                } else {
                    result.setText("You are Healthy");
                    status.setBackgroundColor(getResources().getColor(R.color.HW, getTheme()));
                }
                value.setText("Your BMI is : "+val);
            }
        });

    }
}