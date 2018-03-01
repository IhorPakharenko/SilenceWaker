package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by Isao on 23.02.2018.
 */

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    fun getAll(): Flowable<List<Alarm>>

    @Query("SELECT * FROM alarms WHERE id=:id LIMIT 1")
    fun getById(id: Int): Alarm

    @Insert
    fun insert(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)
}
