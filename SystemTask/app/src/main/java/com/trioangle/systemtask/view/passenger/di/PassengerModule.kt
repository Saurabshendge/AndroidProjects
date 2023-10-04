package com.trioangle.systemtask.view.passenger.di

import com.trioangle.systemtask.module.NewRetrofit
import com.trioangle.systemtask.module.WebServices
import com.trioangle.systemtask.view.passenger.data.PassengerRepository
import com.trioangle.systemtask.view.passenger.data.PassengerRepositoryImpl
import com.trioangle.systemtask.view.passenger.data.dataSource.PassengerRemoteDataSource
import com.trioangle.systemtask.view.passenger.data.dataSourceImpl.PassengerRemoteDataSourceImpl
import com.trioangle.systemtask.view.passenger.interactor.GetPassengerUseCase
import com.trioangle.systemtask.view.passenger.interactor.PassengerInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PassengerModule {

    @Singleton
    @Provides
    fun providePassengerRemoteDataSource(@NewRetrofit webservices: WebServices): PassengerRemoteDataSource {
        return PassengerRemoteDataSourceImpl(webservices)
    }

    @Singleton
    @Provides
    fun providePassengerRepository(remoteDataSource: PassengerRemoteDataSource): PassengerRepository {
        return PassengerRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun providePassengerInteractor(getPassengerUseCase: GetPassengerUseCase): PassengerInteractor {
        return PassengerInteractor(getPassengerUseCase)
    }

}