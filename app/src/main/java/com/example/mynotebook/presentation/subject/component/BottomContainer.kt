package com.example.mynotebook.presentation.subject.component

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mynotebook.R


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomContainer(
    onExpandAddScreen:()->Unit,
    onDismissAddScreen:()->Unit
) {
    var text  by remember {
        mutableStateOf("")
    }


    val focusRequester = FocusRequester()

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp

    ) {
        
        Row() {
            Icon(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_baseline_attach_file_24),
                contentDescription = "")
            OutlinedTextField(
                value = text,
                onValueChange ={
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .padding(8.dp)
                    .focusRequester(focusRequester = focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onExpandAddScreen()
                        }

                    },
                shape = RoundedCornerShape(30.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                     onDismissAddScreen()
                        keyboardController?.hide()
                    }
                )



            )

            Icon(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(8.dp),
                imageVector = Icons.Filled.Send,
                contentDescription = "")
        }
        
    }

    
}