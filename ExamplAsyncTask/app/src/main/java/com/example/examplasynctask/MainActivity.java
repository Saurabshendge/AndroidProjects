package com.example.examplasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progress_bar);

    }
    public void startAsyncTask(View view){
        ExampleAsyncTask task=new ExampleAsyncTask();
        task.execute(10);

    }
    private  class ExampleAsyncTask extends AsyncTask<Integer,Integer,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override 
        protected String doInBackground(Integer... integers) {
            for(int i=0;i<integers[0];i++){
                publishProgress((i*100)/integers[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finished !";

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }

}