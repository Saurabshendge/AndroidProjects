package com.trioangle.systemtask.view.passenger.data

import com.trioangle.systemtask.model.Passengers
import com.trioangle.systemtask.resource.Resource
import com.trioangle.systemtask.resource.WebServicesRequest
import com.trioangle.systemtask.view.passenger.data.dataSource.PassengerRemoteDataSource
import javax.inject.Inject

class PassengerRepositoryImpl @Inject constructor(private val remoteDataSource: PassengerRemoteDataSource):
    PassengerRepository,WebServicesRequest() {
    override suspend fun getPassenger(page:Int,size:Int): Resource<Passengers> {
        return webApiCallRequest { remoteDataSource.getPassenger(page, size) }
    }


}