package com.example.isao.silentwaker.editAlarmActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.isao.silentwaker.R
import kotlinx.android.synthetic.main.activity_edit_alarm.*

class EditAlarmActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alarm)


        //NumberPicker widget to set hours value
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);

        //NumberPicker widget to set minutes value
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);



    }
}
