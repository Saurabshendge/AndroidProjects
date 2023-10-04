package com.trioangle.systemtask.view.passenger.presentation

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trioangle.systemtask.MainActivity
import com.trioangle.systemtask.R
import com.trioangle.systemtask.databinding.FragmentPassengerListBinding
import com.trioangle.systemtask.model.Data
import com.trioangle.systemtask.resource.Resource
import com.trioangle.systemtask.utils.JSONUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PassengerListFragment:DaggerFragment(){

    companion object{
        const val PASSENGER_BUNDLE_KEY = "PASSENGER_BUNDLE_KEY"
    }

    @Inject
    lateinit var viewModel: PassengerViewModel

    private lateinit var binding: FragmentPassengerListBinding
    private var adapter = PassengerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passenger_list,container,false)

        initViewModel()
        getPassengerListObserver(0,10)
        return binding.root
    }

    private fun initViewModel(){
        binding.viewModel = viewModel
    }


    private fun getPassengerListObserver(page:Int,size:Int){
        viewModel.getPassengerUseCase(page, size)
            .observe(this,{
                when(it){
                    is Resource.ConnectionTimeOut -> {
                        Toast.makeText(context, "connection TO", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.FailureJSONObject -> {
                        Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.FailureMessage -> {
                        Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.NoInternet -> {
                        getErrorHandler()
                    }
                    is Resource.Success -> {
                        onConfigPassengerListSuccessHandler(ArrayList(it.data.data))
                    }
                }
            })
    }

    private fun onConfigPassengerListSuccessHandler(data:ArrayList<Data>){
        adapter = PassengerAdapter()
        binding.rvPassenger.adapter = adapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.rvPassenger.layoutManager = layoutManager
        adapter.setListItems(data)
        if (data.isEmpty()){
            adapter.setEmptyView(R.layout.empty_view)
        }
        adapter.mItemClickListener={passengers,position ->
            onRedirectViewPassengerDetails(passengers)
        }
        hideLoading()
    }

    private fun onRedirectViewPassengerDetails(passenger:Data){
        val bundle = Bundle()
        bundle.putString(PASSENGER_BUNDLE_KEY,JSONUtils.toJson(passenger))
        findNavController().navigate(R.id.passengerDetailFragment,bundle)
    }



    private fun showLoading() {
        binding.placeHolder.onConfigLoading(true)
    }

    private fun hideLoading() {
        binding.placeHolder.onConfigLoading(false)
    }

    private fun showErrorToast() {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
    }


    private fun getErrorHandler() {
        binding.placeHolder.onConfigError(true)
        showErrorToast()
    }

}