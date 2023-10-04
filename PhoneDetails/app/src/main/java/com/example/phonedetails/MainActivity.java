package com.example.phonedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSatrt;
    TextView varTest;
    String info;
    String strPhoneType;
    static final int PERMISSIN_READ_STATE=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Start(View view){
        //int pernissionCheck= ContextCompat.checkSelfPermission(this,Manifest.permission.READPHO);
    }

}