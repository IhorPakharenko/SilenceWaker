package com.example.isao.silentwaker.data.db

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Isao on 24.02.2018.
 */

object Converters {
    @JvmStatic
    private val formatter = DateTimeFormatter.ISO_OFFSET_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetTime(value: String?): OffsetTime? {
        return value?.let { formatter.parse(it, OffsetTime::from) }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetTime(value: OffsetTime?) = value?.format(formatter)
}
