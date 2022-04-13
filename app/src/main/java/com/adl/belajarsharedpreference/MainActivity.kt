package com.adl.belajarsharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Toast
import it.ngallazzi.fancyswitch.FancyState
import it.ngallazzi.fancyswitch.FancySwitch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     var sliderValue: Int =0
    var groupCombo: Int = 0
    var switchCombo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreference = getSharedPreferences("Input_setting", Context.MODE_PRIVATE)

        Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()
////seakbar nya //////
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                txtSlider.setText("Nilainya adalah:${p1.toString()}")
                sliderValue = p1

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
////option radio///
        radioGrup.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

                val result = when (p1){
                    R.id.radioButton -> " Option 1"
                    R.id.radioButton2 -> "Option 2"
                    R.id.radioButton3 -> "Option 3"
                    else -> "No Option"
                }
                Toast.makeText(this@MainActivity," Pilihannya adalah ${result}", Toast.LENGTH_LONG).show()
                groupCombo= p1
            }
        })
        ///tombol switch///
        switch1.setOnCheckedChangeListener { p0, p1 ->
            Toast.makeText(this@MainActivity,"nilainya adalah ${p1}", Toast.LENGTH_SHORT).show()
            switch1.setText(p1.toString())
            switchCombo = p1
        }
        fancySwitch.setSwitchStateChangedListener(object : FancySwitch.StateChangedListener{
            override fun onChanged(newState: FancyState) {
                if(newState.id == FancyState.State.ON){
                    Toast.makeText(this@MainActivity,"Nilainya adalah on",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@MainActivity,"Nilainya adalah off",Toast.LENGTH_SHORT).show()
                }
            }
        })

        switch1.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                Toast.makeText(this@MainActivity," Nilainya adalah ${p1}", Toast.LENGTH_LONG).show()
                switch1.setText(p1.toString())
                switchCombo = p1
            }
        })
////save ///

        btnSave.setOnClickListener({
            var editor = sharedPreference.edit()
            editor.putInt("Slinder",sliderValue)
            editor.putInt("combo",groupCombo)
            editor.putBoolean("combo",switchCombo)
            editor.putString("text",txtInput.text.toString())
            editor.commit()

        })

        loadSave(sharedPreference)

    }
    private fun loadSave(sharedPreferences: SharedPreferences){

        sliderValue = sharedPreferences.getInt("slider",0)
        groupCombo = sharedPreferences.getInt("combo",0)
        switchCombo = sharedPreferences.getBoolean("switch",false)
        txtInput.setText(sharedPreferences.getString("text",""))

        seekBar.setProgress(sliderValue)
        radioGrup.check(groupCombo)
        switch1.setChecked(switchCombo)

    }

}