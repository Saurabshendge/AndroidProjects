package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,gmail;
    Button button,button1;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.edittext1);
        gmail=findViewById(R.id.edittext2);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);

        sp=getSharedPreferences("MyuserPref", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namestr=name.getText().toString();
                String gmailstr=gmail.getText().toString();

                SharedPreferences.Editor editor=sp.edit();
                editor.putString("name",namestr);
                editor.putString("gamil",gmailstr);
                Toast.makeText(MainActivity.this, "Information saved", Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Otherpage1.class);
                startService(intent);
            }
        });


    }
}