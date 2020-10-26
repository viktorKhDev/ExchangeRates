package com.viktor.kh.dev.exchangerates.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.viktor.kh.dev.exchangerates.repository.AppDatabase
import com.viktor.kh.dev.exchangerates.repository.ExchangeRateDao
import com.viktor.kh.dev.exchangerates.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule() {

    @Provides
    @Singleton
    fun provideDataBase(context: Context):AppDatabase{
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): ExchangeRateDao{
        return db.exchangeRateDao()
    }

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return Repository()
    }


}