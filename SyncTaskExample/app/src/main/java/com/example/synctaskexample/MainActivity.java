package com.example.synctaskexample;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    ProgressBar progressBar1,progressBar2;
    TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        progressBar1=findViewById(R.id.progressbar1);
        progressBar2=findViewById(R.id.progressbar2);
        text1=findViewById(R.id.text1);

        text1.setText("");
        progressBar2.setVisibility(View.INVISIBLE);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadTask uploadTask=new UploadTask();
                uploadTask.execute();

                for(int i=0;i<10;i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    class UploadTask extends AsyncTask<Void,Integer,String>{


        @Override
        protected void onPreExecute() {
            //Log.i(TAG, "do in background string passed :"+strings[0])
        text1.setText("Uploading data..");
            progressBar2.setVisibility(View.VISIBLE);
            btn1.setEnabled(false);

        }

        @Override
        protected String doInBackground(Void... voids) {


            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(1);
            }

            return " finally task is complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar1.setProgress(values[0] + 1);

        }

        @Override
        protected void onPostExecute(String string) {
            Log.i(TAG ,"OnPostExecute :" +Thread.currentThread().getName());
         text1.setText(string);
            progressBar2.setVisibility(View.INVISIBLE);
            btn1.setEnabled(true);


        }
    }
}