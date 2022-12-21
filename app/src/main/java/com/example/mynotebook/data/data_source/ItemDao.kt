package com.example.mynotebook.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynotebook.domain.model.Item
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.model.SubjectWithTopics

import com.example.mynotebook.domain.model.Topic
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {


    @Insert
    fun insertTopic(topic: Topic)

    @Insert
    fun insertItem(item: Item)

    @Query("Select * from subject where id = :subjectId")
    fun getSubjectWithTopics(subjectId:Int): Flow<SubjectWithTopics>

    @Query("Select * from item where id =:topicId")
    fun getAllItems(topicId: Int): Flow<List<Item>>
}