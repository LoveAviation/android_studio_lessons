package com.example.myapplication

import android.os.Bundle

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namespace = binding.NameInputLayout
        val name = binding.nameEditText
        val phone = binding.phoneEditText
        val m = binding.MaleButton
        val f = binding.FemaleButton
        val checkbox1 = binding.AuthorizationCheck
        val checkbox2 = binding.NewsCheck
        val notifswitch = binding.notificationSwitch
        val saveBut = binding.saveButton

        name.doAfterTextChanged{
            if(name.text?.length in 1..40){
                namespace.isErrorEnabled = false
            }else{
                namespace.isErrorEnabled = true
            }
            saveBut.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }

        phone.doAfterTextChanged {
            saveBut.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            saveBut.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }


        binding.notificationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                checkbox1.isEnabled = true
                checkbox2.isEnabled = true
            }else{
                turnOff(checkbox1)
                turnOff(checkbox2)
            }
            saveBut.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }

        checkbox1.setOnClickListener {
            binding.saveButton.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }
        checkbox2.setOnClickListener {
            binding.saveButton.isEnabled = check(name, phone, m, f, checkbox1, checkbox2, notifswitch)
        }


        binding.saveButton.setOnClickListener {
            Snackbar.make(it, "Изменения сохранены", Snackbar.LENGTH_SHORT).show()
        }

        val progrBar = binding.progressBar
        val randomNumber = Random.nextInt(101)
        progrBar.progress = randomNumber
        binding.counter.setText("$randomNumber/100")
    }

    fun checkNotifications(check1: CheckBox, check2: CheckBox, notif: MaterialSwitch):Boolean{
        if(!notif.isChecked) return true
        else if((check1.isChecked || check2.isChecked) && notif.isChecked) return true
        else return false
    }

    fun turnOff(obj : CheckBox){
        obj.isEnabled = false
        obj.isChecked = false
    }

    fun check(name: EditText, phone: EditText, Male: RadioButton, Female: RadioButton, check1: CheckBox, check2: CheckBox, notif: MaterialSwitch): Boolean{
        if(name.text?.trim()?.length!! in 1..40 &&
            phone.text?.length!! > 0 &&
            (Male.isChecked || Female.isChecked) &&
            checkNotifications(check1, check2, notif)) return true
        else return false
    }

}
