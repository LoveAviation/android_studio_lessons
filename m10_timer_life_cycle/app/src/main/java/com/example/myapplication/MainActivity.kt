package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var counter = 10
    val handler = Handler(Looper.getMainLooper())
    var isWorking = false
    lateinit var timerThread: Thread

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null) {
            binding.circleBar.max = savedInstanceState.getInt("max")
            counter = savedInstanceState.getInt("count")
            isWorking = savedInstanceState.getBoolean("isWorking")
            handler.post {
                update()
                if (isWorking) startTimer()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleBar.max = counter

        with(binding){
            slider.addOnChangeListener { _, value, _ ->
                counter = value.toInt()
                circleBar.max = counter
                circleBar.progress = 0
                timerText.text = counter.toString()
            }

            bottomButton.setOnClickListener {
                if(!isWorking && counter > 0) startTimer()
                else stopTimer()
            }
        }
    }

    fun update(){
        binding.timerText.text = counter.toString()
        binding.circleBar.progress = counter
        if(isWorking) {
            binding.slider.visibility = View.GONE
            binding.bottomButton.setText(R.string.stop)
        }
        else{
            binding.circleBar.progress = binding.circleBar.max
            binding.bottomButton.text = getText(R.string.start)
            binding.slider.visibility = View.VISIBLE
        }
    }

    fun startTimer(){
        isWorking = true
        timerThread = Thread{
            while (counter > 0 && isWorking){
                counter --
                handler.post { update() }
                Thread.sleep(1000)
            }
            isWorking = false
            handler.post { stopTimer() }
        }
        timerThread.start()
    }

    fun stopTimer(){
        isWorking = false
        update()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isWorking", isWorking)
        outState.putInt("max", binding.circleBar.max)
        outState.putInt("count", counter)
        super.onSaveInstanceState(outState)
    }
}