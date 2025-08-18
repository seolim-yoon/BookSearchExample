package com.example.data.di

import android.content.Context
import com.example.data.datasource.local.db.AppDatabase
import com.example.data.datasource.local.db.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideBookDao(appDatabase: AppDatabase): BookDao = appDatabase.bookDao()
}