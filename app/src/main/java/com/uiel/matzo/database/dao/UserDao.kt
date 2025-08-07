package com.uiel.matzo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uiel.matzo.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun fetchAll(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user WHERE id = :id")
    fun deleteById(id: Long)

    @Update
    fun update(userEntity: UserEntity)
}