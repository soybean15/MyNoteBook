package com.example.mynotebook.presentation.subject.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ColumnItem(
    title: String,
    content:String,
    color: Color
){

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        color = color
    ) {
        Column() {
            Text(modifier = Modifier.padding(8.dp),
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp
                ),
                color = Color.White
            )
            Text(modifier = Modifier.padding(8.dp).fillMaxWidth(),
                text = content,
                style =MaterialTheme.typography.body1
                ,
                color = Color.White
                )

        }
    }

}