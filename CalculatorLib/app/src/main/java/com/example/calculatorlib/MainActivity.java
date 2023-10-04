package com.example.calculatorlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculatorlib.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'calculatorlib' library on application startup.
    static {
        System.loadLibrary("calculatorlib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());



        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_1 = binding.value1.getText().toString();
                String str_2 = binding.value2.getText().toString();
                int value_1 = Integer.parseInt(str_1);
                int value_2 = Integer.parseInt(str_2);
                binding.sampleText.setText(MathOperations('+',value_1,value_2)+" ");
            }
        });
        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_1 = binding.value1.getText().toString();
                String str_2 = binding.value2.getText().toString();
                int value_1 = Integer.parseInt(str_1);
                int value_2 = Integer.parseInt(str_2);
                binding.sampleText.setText(MathOperations('-',value_1,value_2)+" ");            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_1 = binding.value1.getText().toString();
                String str_2 = binding.value2.getText().toString();
                int value_1 = Integer.parseInt(str_1);
                int value_2 = Integer.parseInt(str_2);
                binding.sampleText.setText(MathOperations('*',value_1,value_2)+" ");            }
        });
        binding.btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_1 = binding.value1.getText().toString();
                String str_2 = binding.value2.getText().toString();
                int value_1 = Integer.parseInt(str_1);
                int value_2 = Integer.parseInt(str_2);
                binding.sampleText.setText(MathOperations('/',value_1,value_2)+" ");            }
        });


    }

    /**
     * A native method that is implemented by the 'calculatorlib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int MathOperations(char op,int a,int b);
}