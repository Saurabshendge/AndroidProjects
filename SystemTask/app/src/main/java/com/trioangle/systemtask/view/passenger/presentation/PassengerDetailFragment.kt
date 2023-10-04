package com.trioangle.systemtask.view.passenger.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.trioangle.systemtask.R
import com.trioangle.systemtask.databinding.FragmentPassengerDetailBinding
import com.trioangle.systemtask.model.Data
import com.trioangle.systemtask.model.Passengers
import com.trioangle.systemtask.utils.JSONUtils

class PassengerDetailFragment:Fragment() {

    private lateinit var binding:FragmentPassengerDetailBinding
    private var mPassengerJsonString = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passenger_detail,container,false)
        getData()
        onConfigCompanyDetails()
        return binding.root
    }

    private fun getData(){
        arguments?.let {
            mPassengerJsonString = it.getString(PassengerListFragment.PASSENGER_BUNDLE_KEY,"")
        }
    }

    private fun onConfigCompanyDetails(){
        val mPassenger = JSONUtils.fromJson(mPassengerJsonString, Data::class.java)
        binding.passengerNameValue.text = mPassenger.name
        mPassenger.airline.forEachIndexed { index, airline ->
            binding.passengerCountryValue.text = airline.country
            binding.passengerIdValue.text = airline.id.toString()
            binding.passengerHQValue.text = airline.head_quaters
            binding.establishedValue.text = airline.established
        }
    }

}