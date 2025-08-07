package com.uiel.matzo.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalTime

@ProvidedTypeConverter
internal class TimeTypeConverter {

    @TypeConverter
    fun stringToLocalTime(value: String): LocalTime = LocalTime.parse(value)

    @TypeConverter
    fun localTimeToString(value: LocalTime): String = value.toString()
}