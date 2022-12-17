package com.example.mynotebook.presentation.subject_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mynotebook.domain.model.Subject

@Composable
fun FavoritesItem(
    subject: Subject,
   onClick:()->Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp).clickable {
                                   onClick()
            },
        color = Color(color = subject.color),
        elevation = 8.dp,
        shape = RoundedCornerShape(20)

    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            color = Color.White
        )

    }



}