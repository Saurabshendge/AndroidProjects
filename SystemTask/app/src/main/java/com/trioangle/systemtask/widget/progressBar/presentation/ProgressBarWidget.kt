package com.trioangle.systemtask.widget.progressBar.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.trioangle.systemtask.databinding.LayoutProgeressBarWidgetBinding

class ProgressBarWidget @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding:LayoutProgeressBarWidgetBinding

    init {
        initView()
    }

    private fun initView(){
            binding = LayoutProgeressBarWidgetBinding.inflate(LayoutInflater.from(context),this,true)
            removeAllViews()
            addView(binding.root)
            binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)

    }

    fun onConfigLoading(isLoadingVisible:Boolean){
        binding.clParent.isVisible = isLoadingVisible
    }

}