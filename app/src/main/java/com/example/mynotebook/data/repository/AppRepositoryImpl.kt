package com.example.mynotebook.data.repository

import com.example.mynotebook.data.data_source.SubjectDao
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val dao: SubjectDao

) :AppRepository{



    override suspend fun subjects(): Flow<List<Subject>> = dao.getSubjects()


    override suspend fun insertSubject(subject: Subject) = dao.insertSubject(subject)

    override suspend fun deleteSubject(id: Long) = dao.deleteProduct(id)

    override suspend fun findSubject(name: String) :Flow<List<Subject>> = dao.findSubject(name)

    override suspend fun addToFavorites(subject: Subject) = dao.addToFavorites(subject)

    override suspend fun getFavorites(): Flow<List<Subject>> = dao.getFavorites()



}