package com.example.mynotebook.domain.repository

import com.example.mynotebook.domain.model.Item
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.model.SubjectWithTopics

import com.example.mynotebook.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun insertTopic(topic: Topic)

    suspend fun insertItem(item:Item)

   // suspend fun getTopics(subjectId:Int): Flow<List<Topic>>

    suspend fun getItems(subjectId: Int):  Flow<List<Item>>

    suspend fun getSubjectWithTopics(subjectId: Int):Flow<SubjectWithTopics>
}