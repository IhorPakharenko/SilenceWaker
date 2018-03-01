package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetTime

/**
 * Created by Isao on 23.02.2018.
 */


@Entity(tableName = "Alarms")
data class Alarm(@PrimaryKey(autoGenerate = true) val id: Int, var volume: Int, var time: OffsetTime)