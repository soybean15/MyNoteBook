package com.example.mynotebook.data.repository

import com.example.mynotebook.data.data_source.ItemDao
import com.example.mynotebook.domain.model.Item
import com.example.mynotebook.domain.model.SubjectWithTopics

import com.example.mynotebook.domain.model.Topic
import com.example.mynotebook.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val dao: ItemDao
) :ItemRepository{
    override suspend fun insertTopic(topic: Topic) {
       dao.insertTopic(topic)
    }

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item)
    }

//    override suspend fun getTopics(subjectId: Int): Flow<List<Topic>> {
//        return dao.getAllTopics(subjectId)
//    }

    override suspend fun getItems(subjectId: Int): Flow<List<Item>> {
        return dao.getAllItems(subjectId)
    }

    override suspend fun getSubjectWithTopics(subjectId: Int): Flow<SubjectWithTopics> {
        return dao.getSubjectWithTopics(subjectId)
    }
}