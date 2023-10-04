package com.example.dbcreation;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name, uname, pas;
    private Button add;
    private DBConnect dbc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbc = new DBConnect(getApplicationContext());
        name = findViewById(R.id.name);
        uname = findViewById(R.id.uname);
        pas = findViewById(R.id.pas);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbc.insertData(new Data("Pankaj", "Pankaj28", "1234"));
            }
        });
    }
}