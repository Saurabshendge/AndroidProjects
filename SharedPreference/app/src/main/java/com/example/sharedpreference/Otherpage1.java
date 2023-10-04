package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Otherpage1 extends AppCompatActivity {
    EditText t1,t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherpage1);

        t1=findViewById(R.id.edittext1);
        t2=findViewById(R.id.edittext2);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("MyuserPref", Context.MODE_PRIVATE);
        String name=sp.getString("name","");
        String gmail=sp.getString("gmail","");

        t1.setText(name);
        t2.setText(gmail);
    }
}