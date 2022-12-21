package com.example.mynotebook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow
import java.io.Serializable


@Entity
data class Subject (
    var name: String,
    var timeStamp:Long,
    val color:Int,
    var favorites:Boolean
    ): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0



}

data class SubjectWithTopics(
    @Embedded val subject: Subject,
    @Relation(parentColumn = "id", entityColumn = "subjectId", entity = Topic::class)val topics:List<Topic>
)
