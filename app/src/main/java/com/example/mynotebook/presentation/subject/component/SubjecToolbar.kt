package com.example.mynotebook.presentation.subject.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mynotebook.domain.model.Subject

@Composable
fun SubjectToolbar(
    subject: Subject,
    navController: NavController
){
    
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp,
        color = Color(subject.color)
          
    ) {
        Column() {
            Row() {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                        navController.popBackStack()
                        }
                        .fillMaxWidth(.1f),
                    tint = Color.White

                )

                Text(
                    text = subject.name,
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .align(CenterVertically),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.h5


                )
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            // Implement back action here
                        }
                        .fillMaxWidth(),
                    tint = Color.White
                )



            }
            
        }
        
    }
}