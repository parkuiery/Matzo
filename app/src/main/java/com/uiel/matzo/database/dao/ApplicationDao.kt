package com.uiel.matzo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.model.MealType
import java.time.LocalDate

@Dao
interface ApplicationDao {

    @Query("SELECT * FROM application")
    fun fetchAll(): List<ApplicationEntity>

    @Query("SELECT * FROM application WHERE date = :date")
    fun findByDate(date: LocalDate): List<ApplicationEntity>?

    @Query("SELECT * FROM application WHERE date BETWEEN :startDate AND :endDate")
    fun findByDateRange(startDate: LocalDate, endDate: LocalDate): List<ApplicationEntity>

    @Query("SELECT * FROM application WHERE date = :date AND meal_type = :mealType")
    fun findByMeal(date: LocalDate, mealType: MealType): List<ApplicationEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(applicationEntity: ApplicationEntity)

    @Delete
    fun delete(applicationEntity: ApplicationEntity)

    @Query("DELETE FROM application WHERE id = :id")
    fun deleteById(id: Long)
}