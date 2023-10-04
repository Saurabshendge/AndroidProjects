package com.example.typelength;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.typelength.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'typelength' library on application startup.
    static {
        System.loadLibrary("typelength");
    }
    TextView txtView;
    EditText txtInput;
    Button enterBtn;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txtView=findViewById(R.id.txtViewID);
        txtInput=findViewById(R.id.txtInputID);
        enterBtn=findViewById(R.id.enterbutton);


        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Example of a call to a native method
       // TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'typelength' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}