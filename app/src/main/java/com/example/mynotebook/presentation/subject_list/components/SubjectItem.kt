package com.example.mynotebook.presentation.subject_list.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.mynotebook.R
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.ui.theme.MyNoteBookTheme
import com.example.mynotebook.ui.theme.pink
import kotlin.math.exp


@Composable
fun SubjectItem(
    subject: Subject,
    onConvertDateToString:(Long)->String,
    onAddToFavorites:(Subject)->Subject
){
//
//    var favorite by remember  {
//        mutableStateOf(false)
//    }

   var favorites by remember {

       mutableStateOf(subject.favorites)
   }

    var expanded by remember {
        mutableStateOf(false)
    }
    val listItems = arrayOf("Edit", "Delete")

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
                    color = Color.White,




                )
                IconButton(
                    onClick = { expanded = !expanded },

                ) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expanded  ,
                    onDismissRequest = { expanded =false },
                    offset = DpOffset(x = (-66).dp, y = (-10).dp)
                ) {
                    listItems.forEachIndexed { index, itemValue ->
                        DropdownMenuItem(
                            onClick = {

                                if(index == 0){
                                    //edit
                                }else{
                                    //delete
                                }
                            },

                            ) {
                            Text(text = itemValue)
                        }
                    }
                }
            }

            Spacer(
                modifier =Modifier.padding(8.dp) )

            Row() {
                Text(
                    text = onConvertDateToString(subject.timeStamp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    color = Color.White,

                    )





                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .height(30.dp)
                        .width(30.dp),
                    onClick = {

                        favorites = onAddToFavorites(subject).favorites
                        Log.d("favorites", "Fav ${subject.favorites}")

                    }


                ){
                    Icon(

                        Icons.Filled.Favorite
                                   ,
                        contentDescription = "",
                        tint =if ( favorites){
                            Log.d("fromIcon","fave ${subject.favorites}")
                            pink
                        }else{
                            Color.White
                        }

                    )
                }
            }




        }

    }

}