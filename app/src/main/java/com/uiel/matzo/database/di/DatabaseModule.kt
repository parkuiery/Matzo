package com.uiel.matzo.database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.uiel.matzo.database.MatzoDatabase
import com.uiel.matzo.database.converter.DateTypeConverter
import com.uiel.matzo.database.converter.TimeTypeConverter
import com.uiel.matzo.database.converter.UserTypeConverter
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideMatzoDatabase(
        @ApplicationContext context: Context,
        gson: Gson,
    ): MatzoDatabase = Room.databaseBuilder(
        context = context,
        klass = MatzoDatabase::class.java,
        name = "matzo-database"
    ).addTypeConverter(
        DateTypeConverter()
    ).addTypeConverter(
        UserTypeConverter(gson = gson)
    ).addTypeConverter(
        TimeTypeConverter()
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: MatzoDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideApplicationDao(db: MatzoDatabase): ApplicationDao = db.applicationDao()
}