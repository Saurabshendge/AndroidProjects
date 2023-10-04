package com.trioangle.systemtask.view.passenger.interactor

import com.trioangle.systemtask.view.passenger.data.PassengerRepository
import javax.inject.Inject

class GetPassengerUseCase @Inject constructor(private val repository: PassengerRepository) {

    suspend fun execute(page:Int,size:Int)=repository.getPassenger(page, size)
}