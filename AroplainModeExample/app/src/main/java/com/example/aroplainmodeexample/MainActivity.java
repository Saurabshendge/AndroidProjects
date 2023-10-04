package com.example.aroplainmodeexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=findViewById(R.id.text1);
    }

    public void buttonCheckAirplaneModeStatus(View view){

        if(Settings.Global.getInt(this.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0)!=0){
            text1.setText("Airplane Mode ON");
            Toast.makeText(this, "Airplane Mode ON", Toast.LENGTH_SHORT).show();
        }
        else{
            text1.setText("Airplane Mode off");
            Toast.makeText(this, "Airplane Mode off", Toast.LENGTH_SHORT).show();
        }
    }
}