package com.example.mynotebook.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotebook.domain.model.Subject

@Database(
    entities = [Subject::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract val appDao: SubjectDao

    companion object {
        const val DATABASE_NAME = "notebook_db"

        }
}
