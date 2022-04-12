package com.adl.belajarsharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext,"Test",Toast.LENGTH_LONG).show()

        val sharedPreferences = getSharedPreferences("login_setting", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username","")
        val isChecked = sharedPreferences.getBoolean("checkbox",false)

        txtUsername.setText(username)
        checkBox.setChecked(isChecked)

        btnLogin.setOnClickListener({
            if (checkBox.isChecked){
                var editor = sharedPreferences.edit()
                editor.putString("username",txtUsername.text.toString())
                editor.putBoolean("checkbox",true)
                editor.commit()
            }
        })
    }
}