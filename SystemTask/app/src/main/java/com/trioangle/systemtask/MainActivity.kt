package com.trioangle.systemtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.trioangle.systemtask.R
import com.trioangle.systemtask.app.App
import com.trioangle.systemtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this,R.color.white)

        initView()
        initNavigationContainer()

    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    private fun initNavigationContainer(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_passenger) as NavHostFragment
        navController = navHostFragment.navController

    }

}