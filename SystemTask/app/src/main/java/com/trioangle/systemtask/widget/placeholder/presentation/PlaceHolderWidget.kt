package com.aravinth.srinivasaploypacksdk.widget.placeholder.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import com.trioangle.systemtask.databinding.LayoutPlaceHolderBinding


class PlaceHolderWidget @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutPlaceHolderBinding

    init {
        initView()
    }

    private fun initView(){
        binding = LayoutPlaceHolderBinding.inflate(LayoutInflater.from(context),this,true)
        removeAllViews()
        addView(binding.root)
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
    }

    fun onConfigError(isErrorVisible:Boolean){
        binding.errorWidget.isVisible=isErrorVisible
    }

    fun onConfigLoading(isLoadingVisible:Boolean){
        binding.progressBar.isVisible=isLoadingVisible
    }



}