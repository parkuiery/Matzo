package com.uiel.matzo.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDate

@ProvidedTypeConverter
internal class DateTypeConverter {

    @TypeConverter
    fun stringToLocalDate(value: String): LocalDate = LocalDate.parse(value)

    @TypeConverter
    fun localDateToSting(value: LocalDate): String = value.toString()
}