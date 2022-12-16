package com.example.mynotebook.presentation.subject_list.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mynotebook.R
import com.example.mynotebook.domain.model.Subject

@Composable
fun AppDrawer(
    darkMode:Boolean,
    toggleLightTheme:()->Unit,
    subjects: List<Subject>
){


    Column() {
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .border(.2.dp, color = Color.LightGray)
            .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id =R.drawable.ic_baseline_dark_mode_24 ),
                contentDescription ="",
                modifier = Modifier.align(CenterVertically)

            )
            Text(
                text = "DarkMode",
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(8.dp),
                style = MaterialTheme.typography.h5
            )
            Switch(
                checked =darkMode,
                onCheckedChange = { toggleLightTheme()},
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp,),
            elevation = 8.dp,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = MaterialTheme.colors.surface


        ) {
            Column() {
                Text(
                    text ="Favorites",
                    modifier = Modifier
                        .padding(top= 10.dp, start = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                LazyColumn(){
                    itemsIndexed(
                        items = subjects.sortedByDescending {
                            it.timeStamp

                        }
                    ){_,subject->
                        FavoritesItem(subject = subject)

                    }
                }
            }

        }


    }

}