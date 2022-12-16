package com.example.mynotebook.presentation.subject_list.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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
    onAddToFavorites:(Subject)->Subject,
    favorites :Boolean,
    onClick:()->Unit,
    onOpenDialogClicked:(Subject)->Unit,
    onDialogDismiss:()->Unit,
    showDialog :Boolean,
    subjectToObserve:Subject,
    onDelete:()->Unit

){

    var _favorites=favorites


    //for dropdownMenu
    var expanded by remember {
        mutableStateOf(false)
    }
    val listItems = arrayOf("Edit", "Delete")

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = {
            onClick()
        }),

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


                                }else{

                                    expanded = !expanded
                                    onOpenDialogClicked(subject)



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

                        _favorites = onAddToFavorites(subject).favorites

                    }


                ){
                    Icon(

                        Icons.Filled.Favorite
                                   ,
                        contentDescription = "",
                        tint =if ( _favorites){

                            pink
                        }else{
                            Color.White
                        }

                    )
                }
            }




        }

    }


    ConfirmDialog(
        onDismiss = onDialogDismiss,
        onConfirm = { onDelete() },
        showDialog=showDialog,
        subject = subjectToObserve
    )





}
