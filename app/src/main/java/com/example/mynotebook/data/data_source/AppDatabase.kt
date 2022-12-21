package com.example.mynotebook.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotebook.domain.model.Item
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.model.Topic

@Database(
    entities = [Subject::class, Topic::class,Item::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract val appDao: SubjectDao
    abstract val itemDao:ItemDao

    companion object {
        const val DATABASE_NAME = "notebook_db"

        }
}
