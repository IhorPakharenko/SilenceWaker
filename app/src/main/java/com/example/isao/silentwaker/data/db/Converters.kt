package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Isao on 24.02.2018.
 */

object Converters {
    @JvmStatic
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    @JvmStatic
    fun toLocalTime(value: String): LocalTime = formatter.parse(value, LocalTime::from)

    @TypeConverter
    @JvmStatic
    fun fromLocalTime(value: LocalTime): String = value.format(formatter)
}
