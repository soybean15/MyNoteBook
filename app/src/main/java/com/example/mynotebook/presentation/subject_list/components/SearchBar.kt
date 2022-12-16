package com.example.mynotebook.presentation.subject_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.room.Query


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    text: String,
    onQueryChange:(String)->Unit,
    onExecuteSearch:()->Unit
){

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    value = text,
                    onValueChange = {
                        onQueryChange(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(
                        onGo = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    label = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    },
                    shape = RoundedCornerShape(12.dp)


                )
                IconButton(
                    onClick = { onExecuteSearch() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterVertically)
                ) {
                    Icon(Icons.Filled.Search, contentDescription = "")
                }
            }
        }
    }


}