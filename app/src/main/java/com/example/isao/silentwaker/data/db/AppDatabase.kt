package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


/**
 * Created by Isao on 23.02.2018.
 */

@Database(entities = [Alarm::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "SilentWaker"

        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME).build()
            }
            return INSTANCE as AppDatabase
        }
    }

    abstract fun alarmDao(): AlarmDao
}
