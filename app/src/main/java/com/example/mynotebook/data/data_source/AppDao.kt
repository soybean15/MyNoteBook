package com.example.mynotebook.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynotebook.domain.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert
    fun insertSubject(subject: Subject)


    @Query("SELECT * FROM subject WHERE name = :name")
    fun findSubject(name: String): Flow<List<Subject>>

    @Query("DELETE FROM subject WHERE id = :id")
    fun deleteProduct(id: Long)

    @Query("Select * from Subject")
    fun getSubjects(): Flow<List<Subject>>
}