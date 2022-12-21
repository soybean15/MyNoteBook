package com.example.mynotebook.presentation.subject.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AddItemScreen(){

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White)
        ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                ,
            value = title, onValueChange = {
                 title= it
            },
            label ={ Text(text = "Title")}


        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
              ,
            value =content, onValueChange = {
                content= it
            },
            label ={ Text(text = "Content")}
        )

    }

}