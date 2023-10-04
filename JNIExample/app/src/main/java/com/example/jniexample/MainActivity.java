package com.example.jniexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
       System.loadLibrary("jniexample");
    }


    Button btn;
    EditText text1,text2;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn);
        textView=findViewById(R.id.textView);
        text1=findViewById(R.id.edit1);
        text2=findViewById(R.id.edit2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=Integer.parseInt(text1.getText().toString());
                int value2=Integer.parseInt(text2.getText().toString());


                int result=addition(value1,value2);
                textView.setText(result);
            }
        });

    }

public native int addition(int a,int b);



}