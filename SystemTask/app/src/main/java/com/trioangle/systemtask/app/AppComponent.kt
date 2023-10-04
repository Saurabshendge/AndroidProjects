package com.trioangle.systemtask.app

import android.app.Application
import com.trioangle.systemtask.module.FragmentModule
import com.trioangle.systemtask.module.NetModule
import com.trioangle.systemtask.view.passenger.di.PassengerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentModule::class,
        PassengerModule::class,
        NetModule::class

    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(application: Application): Builder
        fun build(): AppComponent
    }

}