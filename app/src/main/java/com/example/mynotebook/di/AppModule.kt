package com.example.mynotebook.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mynotebook.BaseApplication
import com.example.mynotebook.data.data_source.AppDao
import com.example.mynotebook.data.data_source.AppDatabase
import com.example.mynotebook.data.repository.AppRepositoryImpl
import com.example.mynotebook.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{



    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun provideAppDatabase( app:Application):AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideAppDao(
        appDB: AppDatabase
    ) = appDB.appDao

    @Provides
    @Singleton
    fun provideAppRepository(db:AppDatabase):AppRepository{
        return AppRepositoryImpl(db.appDao)
    }
}