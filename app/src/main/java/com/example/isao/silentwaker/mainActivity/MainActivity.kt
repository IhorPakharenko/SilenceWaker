package com.example.isao.silentwaker.mainActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.isao.silentwaker.Const.Companion.KEY_DB_ID
import com.example.isao.silentwaker.Const.Companion.KEY_EDIT_MODE
import com.example.isao.silentwaker.Const.Companion.KEY_H
import com.example.isao.silentwaker.Const.Companion.KEY_M
import com.example.isao.silentwaker.Const.Companion.KEY_V
import com.example.isao.silentwaker.R
import com.example.isao.silentwaker.data.db.Alarm
import com.example.isao.silentwaker.data.db.AppDatabase
import com.example.isao.silentwaker.editAlarmActivity.EditAlarmActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.OffsetTime

class MainActivity : Activity() {
    private lateinit var alarmsAdapter: AlarmsAdapter

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        //set the default theme back when the app finishes loading
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)

        alarmsAdapter = AlarmsAdapter()

        rv.adapter = alarmsAdapter
        rv.layoutManager = LinearLayoutManager(this)

        setUpAlarmList()

        fab.setOnClickListener {
            val editAlarm = Intent(this, EditAlarmActivity::class.java)

            editAlarm.putExtra(KEY_H, 14)

            editAlarm.putExtra(KEY_M, 45)

            editAlarm.putExtra(KEY_V, 30)

            editAlarm.putExtra(KEY_EDIT_MODE,true)

            editAlarm.putExtra(KEY_DB_ID,1)

            startActivity(editAlarm)

            //todo start DetailActivity / is
            Single.fromCallable {
                database.alarmDao().insert(Alarm(0, 50, OffsetTime.now()))
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe()

        }
    }

    private fun setUpAlarmList() {
        database.alarmDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { alarmsAdapter.updateList(it) }
                .subscribe()
    }
}