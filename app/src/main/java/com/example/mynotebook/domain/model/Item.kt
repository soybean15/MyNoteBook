package com.example.mynotebook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val title:String,
    val content:String,
    val topicId :Int,
    val subjectId:Int
):java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
