package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalTime

/**
 * Created by Isao on 23.02.2018.
 */


@Entity(tableName = "Alarms")
data class Alarm(val time: LocalTime, val volume: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}