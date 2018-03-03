package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Isao on 23.02.2018.
 */

@Database(entities = [Alarm::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "SilentWaker"

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME).build()
            }
            return INSTANCE as AppDatabase
        }

        fun insert(context: Context, alarm: Alarm): Disposable =
            prepareSubscription { getInstance(context).alarmDao().insert(alarm) }


        fun delete(context: Context, alarm: Alarm): Disposable =
            prepareSubscription { getInstance(context).alarmDao().delete(alarm) }


        private fun prepareSubscription(action: () -> Unit) =
                Single.fromCallable { action() }
                        .subscribeOn(Schedulers.io())
                        .subscribe()
    }

    abstract fun alarmDao(): AlarmDao
}
