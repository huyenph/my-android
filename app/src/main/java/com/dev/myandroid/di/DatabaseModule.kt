package com.dev.myandroid.di

import android.content.Context
import androidx.room.Room
import com.dev.myandroid.data.local.persistence.AppDao
import com.dev.myandroid.data.local.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides
    fun provideDBDao(database: AppDatabase) : AppDao = database.appDao()
}