package com.trioangle.systemtask.module

import com.trioangle.systemtask.view.passenger.presentation.PassengerListFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {


    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributePassengerFragment():PassengerListFragment
}