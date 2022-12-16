package com.example.mynotebook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
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
