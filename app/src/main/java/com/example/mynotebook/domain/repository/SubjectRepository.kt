package com.example.mynotebook.domain.repository

import com.example.mynotebook.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {



    suspend fun subjects(): Flow<List<Subject>>

    suspend fun getSubject(id:Int):Flow<Subject>

    suspend fun insertSubject(subject: Subject)

    suspend fun deleteSubject(id: Int)

    suspend fun findSubject(name: String):Flow<List<Subject>>

    suspend fun addToFavorites(subject: Subject)

    suspend fun getFavorites():Flow<List<Subject>>

    suspend fun updateSubject(subject: Subject)



}