package com.example.mynotebook.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotebook.domain.model.Subject

@Database(
    entities = [Subject::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "notebook_db"

        }
}
