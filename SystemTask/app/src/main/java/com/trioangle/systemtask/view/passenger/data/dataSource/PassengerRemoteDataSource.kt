package com.trioangle.systemtask.view.passenger.data.dataSource

import com.trioangle.systemtask.model.Passengers
import retrofit2.Response

interface PassengerRemoteDataSource {

    suspend fun getPassenger(page: Int,size: Int  ):Response<Passengers>
}