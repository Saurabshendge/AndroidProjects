package com.example.calculatorusingaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edittext1,edittext2;
    Button btnMultiply,btnsubstraction,btnAddition,btndivision;
    TextView text1;
    int result;
    IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext1=findViewById(R.id.edittext1);
        edittext2=findViewById(R.id.edittext2);
        btnMultiply=findViewById(R.id.btnMultiply);
        btnsubstraction=findViewById(R.id.btnsubstraction);
        btnAddition=findViewById(R.id.btnAddition);
        btndivision=findViewById(R.id.btndivision);
        text1=findViewById(R.id.text1);
        Intent i=new Intent(this,MyService.class);
        i.setPackage("com.example.calculatorusingaidl");
        bindService(i,mConnection,BIND_AUTO_CREATE);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=Integer.parseInt(edittext1.getText().toString());
                int value2=Integer.parseInt(edittext2.getText().toString());

                try{
                    result=myAidlInterface.miltipleTwoValuesTogether(value1,value2);
                    text1.setText(result+"");
                }catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        btnsubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=Integer.parseInt(edittext1.getText().toString());
                int value2=Integer.parseInt(edittext2.getText().toString());

                try{
                    result=myAidlInterface.substractionTwoValuesTogether(value1,value2);
                    text1.setText(result+"");
                }catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=Integer.parseInt(edittext1.getText().toString());
                int value2=Integer.parseInt(edittext2.getText().toString());

                try{
                    result=myAidlInterface.additionTwoValuesTogether(value1,value2);
                    text1.setText(result+"");
                }catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        btndivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=Integer.parseInt(edittext1.getText().toString());
                int value2=Integer.parseInt(edittext2.getText().toString());

                try{
                    float result1=myAidlInterface.divisionTwoValuesTogether(value1,value2);
                    text1.setText(result1+"");
                }catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });





    }

    ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface=IMyAidlInterface.Stub.asInterface(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}