package com.trioangle.systemtask.view.passenger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trioangle.systemtask.model.Passengers
import com.trioangle.systemtask.resource.Resource
import com.trioangle.systemtask.utils.SingleLiveEvent
import com.trioangle.systemtask.view.passenger.interactor.PassengerInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class PassengerViewModel @Inject constructor(private val passengerInteractor: PassengerInteractor):ViewModel() {

    private val getPassengerUseCase = SingleLiveEvent<Resource<Passengers>>()

    fun getPassengerUseCase(page:Int,size:Int): SingleLiveEvent<Resource<Passengers>> {
        getPassengerUseCase.postValue(Resource.Loading(null))
        viewModelScope.launch {
            getPassengerUseCase.postValue(
                passengerInteractor.getPassengerUseCase.execute(
                    page, size
                )
            )
        }
        return getPassengerUseCase
    }
}