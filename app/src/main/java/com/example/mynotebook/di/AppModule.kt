package com.example.mynotebook.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mynotebook.BaseApplication
import com.example.mynotebook.data.data_source.AppDatabase
import com.example.mynotebook.data.repository.ItemRepositoryImpl
import com.example.mynotebook.data.repository.SubjectRepositoryImpl
import com.example.mynotebook.domain.repository.ItemRepository
import com.example.mynotebook.domain.repository.SubjectRepository
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
    fun provideSubjectRepository(db:AppDatabase):SubjectRepository{
        return SubjectRepositoryImpl(db.appDao)
    }

    @Provides
    fun provideItemDao(
        appDB: AppDatabase
    ) = appDB.itemDao


    @Provides
    @Singleton
    fun provideItemRepository(db:AppDatabase):ItemRepository{
        return ItemRepositoryImpl(db.itemDao)
    }
}