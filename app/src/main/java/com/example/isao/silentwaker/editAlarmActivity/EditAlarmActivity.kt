package com.example.isao.silentwaker.editAlarmActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import com.example.isao.silentwaker.Const.Companion.HOURS_MAX
import com.example.isao.silentwaker.Const.Companion.HOURS_MIN
import com.example.isao.silentwaker.Const.Companion.KEY_DB_ID
import com.example.isao.silentwaker.Const.Companion.KEY_EDIT_MODE
import com.example.isao.silentwaker.Const.Companion.KEY_H
import com.example.isao.silentwaker.Const.Companion.KEY_M
import com.example.isao.silentwaker.Const.Companion.KEY_V
import com.example.isao.silentwaker.Const.Companion.MINUTES_MAX
import com.example.isao.silentwaker.Const.Companion.MINUTES_MIN
import com.example.isao.silentwaker.R
import kotlinx.android.synthetic.main.activity_edit_alarm.*

class EditAlarmActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private var hours: Int = 0
    private var minutes: Int = 0
    private var volume: Int = 0
    private var editMode: Boolean = false //This flag for
    private var dataBaseId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alarm)
        if (intent != null) {

            dataBaseId = intent.getIntExtra(KEY_DB_ID,0)

            hours = intent.getIntExtra(KEY_H, 0)

            minutes = intent.getIntExtra(KEY_M, 0)

            volume = intent.getIntExtra(KEY_V, 0)

            editMode = intent.getBooleanExtra(KEY_EDIT_MODE, false)
        }

        //NumberPicker widget to set hours value
        hourPicker.minValue = HOURS_MIN
        hourPicker.maxValue = HOURS_MAX
        hourPicker.value = hours

        //NumberPicker widget to set minutes value
        minutePicker.minValue = MINUTES_MIN
        minutePicker.maxValue = MINUTES_MAX
        minutePicker.value = minutes

        seekBarVolume.progress = volume
        seekBarVolume.setOnSeekBarChangeListener(this)

        textCancel.setOnClickListener {
            finish()
        }

        textAccept.setOnClickListener {

            if (editMode) {
                editAlarm(dataBaseId,hourPicker.value, minutePicker.value, volume)
            } else {
                addAlarm(hourPicker.value, minutePicker.value, volume)
            }
            finish()
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        volume = progress
        textVolume.text = resources.getText(R.string.volume).toString() + "${' '}" + progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    private fun editAlarm(id: Int, h: Int, m: Int, v: Int) {
        Log.d("log", "Edit Alarm Mode," +
                "ID : $id,"+
                " hours : $h," +
                " minutes : $m," +
                " volume : $v")
    }

    private fun addAlarm(h: Int, m: Int, v: Int) {
        Log.d("log", "Add Alarm Mode," +
                " hours : $h," +
                " minutes : $m," +
                " volume : $v")
    }
}
