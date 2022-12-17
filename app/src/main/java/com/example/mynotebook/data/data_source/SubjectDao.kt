package com.example.mynotebook.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mynotebook.domain.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert
    fun insertSubject(subject: Subject)

    @Query("Select * from subject where id = :id")
    fun getSubject(id:Int):Flow<Subject>


    @Query("SELECT * FROM subject WHERE name like :name || '%'")
    fun findSubject(name: String): Flow<List<Subject>>

    @Query("DELETE FROM subject WHERE id = :id")
    fun deleteProduct(id: Int)

    @Query("Select * from Subject")
    fun getSubjects(): Flow<List<Subject>>

    @Update
    fun addToFavorites(subject: Subject)

    @Query("Select * from subject where favorites = 1")
    fun getFavorites(): Flow<List<Subject>>
}