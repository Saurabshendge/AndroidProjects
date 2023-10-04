package com.trioangle.systemtask.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App:DaggerApplication() {


    companion object{
        private lateinit var appComponent: AppComponent
    }


    public override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent=DaggerAppComponent
            .builder()
            .bindApplication(this)
            .build()
        return appComponent
    }


}