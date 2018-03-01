package com.example.isao.silentwaker.mainActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.isao.silentwaker.R
import com.example.isao.silentwaker.data.db.Alarm
import com.example.isao.silentwaker.data.db.AppDatabase
import com.example.isao.silentwaker.detailActivity.DetailActivity
import com.example.isao.silentwaker.editAlarmActivity.EditAlarmActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.OffsetTime

/**
 * Created by Isao on 23.02.2018.
 */

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
                        startActivity(Intent(this, EditAlarmActivity::class.java))
            //todo start DetailActivity
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