package com.example.alarammanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alarammanager.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CreateNotificationChannel();

        binding.selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();

            }
        });
        binding.setalaram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlaram();

            }
        });

        binding.cancalalaram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlaram();

            }
        });
    }

    private void cancelAlaram() {
        Intent intent=new Intent(this,AlaramReciver.class);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        if(alarmManager==null){
            alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }

    private void setAlaram() {
        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlaramReciver.class);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Alarm Set Succesfully", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {
        picker=new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("select Alaram Time")
                .build();
        picker.show(getSupportFragmentManager(),"foxandroid");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picker.getHour()>12){
                    binding.selectedTime.setText(
                            String.format("%02d",(picker.getHour()-12)+" : "+String.format("%02d",picker.getMinute())+"PM"));
                }else{
                    binding.selectedTime.setText(picker.getHour()+" : "+picker.getMinute()+" AM");
                }
                calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }

    private void CreateNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="foxandroidReminderChannel";
            String description="Channel for Alaram Manahger";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("foxandroid",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}