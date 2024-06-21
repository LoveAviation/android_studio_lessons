package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.myapplication.databinding.CustomViewBinding

class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null): RelativeLayout(context, attrs) {
    private val binding: CustomViewBinding
    init {
        val inflatedView = inflate(context,
            R.layout.custom_view, this)
        binding = CustomViewBinding.bind(inflatedView)
    }

    fun setTopText(text: String){
        binding.topText.text = text
    }
    fun setBottomText(text: String){
        binding.bottomText.text = text
    }


}