package com.trioangle.systemtask.view.passenger.data.dataSourceImpl

import com.trioangle.systemtask.model.Passengers
import com.trioangle.systemtask.module.WebServices
import com.trioangle.systemtask.view.passenger.data.dataSource.PassengerRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PassengerRemoteDataSourceImpl @Inject constructor(private val webServices: WebServices):
    PassengerRemoteDataSource {
    override suspend fun getPassenger(page: Int, size: Int): Response<Passengers> =
        webServices.getPassenger(page, size)


}