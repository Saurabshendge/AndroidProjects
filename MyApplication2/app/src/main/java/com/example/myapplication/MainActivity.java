package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("myapplication");
    }



    TextView txtView;
    EditText txtInput;
    Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView=findViewById(R.id.txtViewID);
        txtInput=findViewById(R.id.txtInputID);
        enterBtn=findViewById(R.id.enterbutton);
        enterBtn.setOnClickListener(this);




    }

    public native int getTxtLen(String txt);


    private void enterText(){
        String txt=txtInput.getText().toString();
        int txtLen=getTxtLen(txt);
        txtView.setText("Text length :" +txtLen);
    }


    @Override
    public void onClick(View v) {
        if(v==enterBtn){
            enterText();
        }
    }
}