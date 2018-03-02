package com.example.isao.silentwaker.editAlarmActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.isao.silentwaker.R
import kotlinx.android.synthetic.main.activity_edit_alarm.*

class EditAlarmActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alarm)


        //NumberPicker widget to set hours value
        hourPicker.minValue = 0
        hourPicker.maxValue = 23

        //NumberPicker widget to set minutes value
        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        seekBarVolume.max = 100
        seekBarVolume.progress = 30

        seekBarVolume.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        textVolume.text = resources.getText(R.string.volume).toString() + "${' '}" + progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}
