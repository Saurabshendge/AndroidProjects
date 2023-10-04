package com.trioangle.systemtask.view.passenger.data

import com.trioangle.systemtask.model.Passengers
import com.trioangle.systemtask.resource.Resource

interface PassengerRepository {
    suspend fun getPassenger(page:Int,size:Int):Resource<Passengers>
}