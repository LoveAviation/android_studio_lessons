package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customView = CustomView(this)
        customView.setTopText("Этот текст был настроен из кода")
        customView.setBottomText("Кода :)")
        setContentView(customView)
    }


}