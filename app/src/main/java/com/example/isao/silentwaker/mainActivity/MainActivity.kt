package com.example.isao.silentwaker.mainActivity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.isao.silentwaker.R
import com.example.isao.silentwaker.data.db.Alarm
import com.example.isao.silentwaker.data.db.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Isao on 23.02.2018.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var alarmsAdapter: AlarmsAdapter

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        //set the default theme back when the app finishes loading
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmsAdapter = AlarmsAdapter()

        //called when a user removes an element from the alarm list by swiping
        val touchHelperCallback = AlarmRecyclerTouchHelper(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            val time = alarmsAdapter.alarms[it.adapterPosition]
                    .time.format(DateTimeFormatter.ofPattern("HH:mm"))
            val alarmBackup = alarmsAdapter.alarms[it.adapterPosition]

            disposables.add(AppDatabase.delete(this, alarmBackup))

            val snackbar = Snackbar.make(activityMainContainer,
                    getString(R.string.alarm_removed, time),
                    Snackbar.LENGTH_LONG)
            snackbar.setAction(getString(R.string.undo)) {
                disposables.add(AppDatabase.insert(this, alarmBackup))
            }
            snackbar.show()
        }

        rvAlarm.adapter = alarmsAdapter
        rvAlarm.layoutManager = LinearLayoutManager(this)
        ItemTouchHelper(touchHelperCallback).attachToRecyclerView(rvAlarm)

        AppDatabase.getInstance(this).alarmDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { alarmsAdapter.alarms = it }
                .subscribe()
                .addTo(disposables)

        fabAddAlarm.setOnClickListener {
            //            startActivity(Intent(this, DetailActivity::class.java))
            //todo start DetailActivity
            disposables.add(AppDatabase.insert(this, Alarm(LocalTime.now(), 50)))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}