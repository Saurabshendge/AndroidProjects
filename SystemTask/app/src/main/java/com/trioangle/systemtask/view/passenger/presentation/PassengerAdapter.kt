package com.trioangle.systemtask.view.passenger.presentation

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.trioangle.systemtask.R
import com.trioangle.systemtask.databinding.LayoutPassengerItemBinding
import com.trioangle.systemtask.model.Data
import com.trioangle.systemtask.model.Passengers

class PassengerAdapter:BaseQuickAdapter<Data,BaseViewHolder>(R.layout.layout_passenger_item){

    var mItemClickListener:((Data,Int) -> Unit)?= null

    fun setListItems(list:ArrayList<Data>){
        data.clear()
        setNewInstance(list)
    }

    override fun convert(holder: BaseViewHolder, item: Data) {
        val binding = DataBindingUtil.bind<LayoutPassengerItemBinding>(holder.itemView)

        binding?.let {
            it.tvPassenger.text = item.name
            it.clParent.setOnClickListener {
                mItemClickListener?.invoke(item,getItemPosition(item))
            }
        }
    }


}