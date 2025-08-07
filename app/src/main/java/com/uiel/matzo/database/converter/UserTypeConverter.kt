package com.uiel.matzo.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.uiel.matzo.database.entity.UserEntity

@ProvidedTypeConverter
class UserTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun userToJson(value: UserEntity): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToUser(value: String): UserEntity {
        return gson.fromJson(value,UserEntity::class.java)
    }
}
