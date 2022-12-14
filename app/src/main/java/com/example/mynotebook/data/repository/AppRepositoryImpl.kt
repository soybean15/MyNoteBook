package com.example.mynotebook.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mynotebook.data.data_source.AppDao
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.AppRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val dao: AppDao

) :AppRepository{



    override suspend fun subjects(): Flow<List<Subject>> = dao.getSubjects()


    override suspend fun insertSubject(subject: Subject) = dao.insertSubject(subject)



    override suspend fun deleteSubject(id: Long) = dao.deleteProduct(id)



    override suspend fun findSubject(name: String) :Flow<List<Subject>> = dao.findSubject(name)



}