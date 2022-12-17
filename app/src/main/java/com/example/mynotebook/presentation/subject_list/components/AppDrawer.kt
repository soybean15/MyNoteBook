package com.example.mynotebook.presentation.subject_list.components

import android.icu.number.Scale
import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mynotebook.R
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.ui.theme.black2
import com.example.mynotebook.ui.theme.brown_900
import com.example.mynotebook.ui.theme.orange_900
import kotlinx.coroutines.delay
import java.time.LocalDateTime

@Composable
fun AppDrawer(
    darkMode:Boolean,
    toggleLightTheme:()->Unit,
    subjects: List<Subject>,
    currentDate:String
){




    Column() {


        NoteBookImage(currentDate)

        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
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

@Composable
fun NoteBookImage(
    currentDate:String
){


    val currentTimeMillis = remember {
        mutableStateOf(System.currentTimeMillis())
    }

    LaunchedEffect(key1 = currentTimeMillis) {
        while (true) {
            delay(250)
            currentTimeMillis.value = System.currentTimeMillis()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){

        Image(
            painter = painterResource(id = R.drawable.note_book),
            contentDescription ="Notebook Image",
            contentScale= ContentScale.Crop
        )

        ImageText()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Transparent,
                        black2
                    ),
                    startY = 300f
                )
            ))


        Box(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ){

            Row (){
                Text(
                    currentDate+" "+DateUtils.formatDateTime(LocalContext.current, currentTimeMillis.value, DateUtils.FORMAT_SHOW_TIME),
                    color = Color.White
                )


            }

        }

    }

}


@Composable
fun ImageText(){
    Box(
        modifier = Modifier.fillMaxSize()



    ){


        ConstraintLayout(
            modifier = Modifier

        ) {
            val (text1, text2, text3) = createRefs()
            val startGuideline = createGuidelineFromStart(220.dp)
            val topGuideline = createGuidelineFromTop(60.dp)



            Text(text = "My",
                modifier = Modifier.constrainAs(text1){
                    top.linkTo(topGuideline)
                    start.linkTo(startGuideline)





                },
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
            Text(text = "NoteBook",
                modifier = Modifier.constrainAs(text2){
                    top.linkTo(text1.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(parent.end)

                },
                style = MaterialTheme.typography.h5,
                color = brown_900
            )
            Text(text = "App",
                modifier = Modifier.constrainAs(text3){
                    top.linkTo(text2.bottom)
                    start.linkTo(startGuideline)


                },
                style = MaterialTheme.typography.h6,
                color = orange_900
            )
        }








    }

}


