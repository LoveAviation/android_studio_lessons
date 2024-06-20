package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var counter = 0
        val status = getText(R.string.status)

        binding.minusBtn.setOnClickListener {
            counter --
            binding.counterTxt.text = counter.toString()
            binding.status.text = "$status ${50 - counter}"
            binding.status.setTextColor(getColor(R.color.dark_blue))
            if(counter <= 0){
                counter = 0
                binding.counterTxt.text = counter.toString()
                binding.status.setTextColor(getColor(R.color.green))
                binding.status.text = getText(R.string.default_status)
                binding.minusBtn.isEnabled = false
                binding.resetBtn.visibility = View.INVISIBLE
            }
        }

        binding.plusBtn.setOnClickListener {
            counter ++
            binding.counterTxt.text = counter.toString()
            binding.status.text = "$status ${50 - counter}"
            binding.status.setTextColor(getColor(R.color.dark_blue))
            if(counter >= 50){
                binding.resetBtn.visibility = View.VISIBLE
                binding.status.text = getText(R.string.expired_status)
                binding.status.setTextColor(getColor(R.color.red))
            }
            binding.minusBtn.isEnabled = true
        }

        binding.resetBtn.setOnClickListener {
            counter = 0
            binding.counterTxt.text = counter.toString()
            binding.status.setTextColor(getColor(R.color.green))
            binding.status.text = getText(R.string.default_status)
            binding.minusBtn.isEnabled = false
            binding.resetBtn.visibility = View.INVISIBLE
        }

    }
}