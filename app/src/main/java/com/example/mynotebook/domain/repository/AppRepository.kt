package com.example.mynotebook.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mynotebook.domain.model.Subject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface AppRepository {



    suspend fun subjects(): Flow<List<Subject>>

    suspend fun getSubject(id:Int):Flow<Subject>

    suspend fun insertSubject(subject: Subject)

    suspend fun deleteSubject(id: Int)

    suspend fun findSubject(name: String):Flow<List<Subject>>

    suspend fun addToFavorites(subject: Subject)

    suspend fun getFavorites():Flow<List<Subject>>



}