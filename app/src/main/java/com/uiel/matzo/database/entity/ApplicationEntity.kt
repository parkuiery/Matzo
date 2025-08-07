package com.uiel.matzo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "application")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "application_time") val applicationTime: LocalTime,
    @ColumnInfo(name = "meal_type") val mealType: MealType,
    @ColumnInfo(name = "working_type") val workingType: WorkingType,
    @ColumnInfo(name = "user") val user: UserEntity,
)
