package com.trioangle.systemtask.widget.errorWidget.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.trioangle.systemtask.databinding.LayoutErrorWidgetBinding


class ErrorWidget @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {



    private lateinit var binding:LayoutErrorWidgetBinding

    init {
        initView()
    }

    private fun initView(){
        binding = LayoutErrorWidgetBinding.inflate(LayoutInflater.from(context),this,true)
        removeAllViews()
        addView(binding.root)
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
    }


}