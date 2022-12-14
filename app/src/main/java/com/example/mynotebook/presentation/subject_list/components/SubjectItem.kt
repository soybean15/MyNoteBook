package com.example.mynotebook.presentation.subject_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.mynotebook.R
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.ui.theme.MyNoteBookTheme

@Composable
fun SubjectItem(
    subject: Subject,
    onConvertDateToString:(Long)->String
){



    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),

        elevation = 8.dp,
        color = Color(color = subject.color),
        shape = RoundedCornerShape(5)
    ) {
        Column() {
            Row() {
                Text(
                    text = subject.name,
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,




                )
                IconButton(
                    onClick = {  }
                ) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "")
                }
            }

            Spacer(
                modifier =Modifier.padding(8.dp) )

            Text(
                text = onConvertDateToString(subject.timeStamp),
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(8.dp),

                )



        }

    }
}