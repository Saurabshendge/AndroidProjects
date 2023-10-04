package com.example.practiceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practiceapp.databinding.ActivityMainBinding;
import com.example.practiceapp.databinding.ActivityMyFragmentBinding;

public class MyFragment extends AppCompatActivity {
    ActivityMyFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

        binding = ActivityMyFragmentBinding.inflate(getLayoutInflater());
        NavHostFragment navHostFragment = new NavHostFragment();
        NavController navController = navHostFragment.getNavController();

        /*binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*setContentView(binding.getRoot());
                NewsFragment newsFragment = new NewsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.replace, newsFragment);
                transaction.commit();*//*
            }
        });*/
    }
}