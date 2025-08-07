package com.uiel.matzo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uiel.matzo.database.converter.DateTypeConverter
import com.uiel.matzo.database.converter.TimeTypeConverter
import com.uiel.matzo.database.converter.UserTypeConverter
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.dao.UserDao
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ApplicationEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    value = [
        DateTypeConverter::class,
        UserTypeConverter::class,
        TimeTypeConverter::class,
    ],
)
abstract class MatzoDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun applicationDao(): ApplicationDao
}
