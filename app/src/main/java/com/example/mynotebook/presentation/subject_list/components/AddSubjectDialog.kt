package com.example.mynotebook.presentation.subject_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mynotebook.domain.model.Subject

@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onTextChange:(String)->Unit,
    onExecuteAction:()->Unit,
    initial:String,
    text: String,
    title: String



) {

    Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(
        dismissOnBackPress = false,dismissOnClickOutside = false
    )
    ) {
        var warning by remember {
            mutableStateOf("")
        }
        var success by remember {
            mutableStateOf(false)
        }

        var isInitial by remember{
            mutableStateOf(true)
        }




            Card(
                //shape = MaterialTheme.shapes.medium,
                shape = RoundedCornerShape(10.dp),
                // modifier = modifier.size(280.dp, 240.dp)
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp
            ) {


                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(MaterialTheme.colors.primary),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Text(
                            text =title,

                        )


                    }

                    OutlinedTextField(
                        value = if (isInitial) initial else text,
                        label = { Text(text = "Enter Subject") },
                        onValueChange = {
                            onTextChange(it)
                            if(it.isNotEmpty()){
                                warning = ""

                            }
                            isInitial = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            ,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.surface,
                            unfocusedBorderColor = MaterialTheme.colors.onSurface),


                        )




                    if(!success){
                        Text(
                            text =warning,
                            color = Color.Red,
                            modifier = Modifier.padding(8.dp)

                        )
                    }


                    Row(Modifier) {
                        OutlinedButton(
                            onClick = { onDismiss() },
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1F)
                        ) {
                            Text(text = "Cancel")
                        }


                        Button(
                            onClick = {
                                if(text.isEmpty()){
                                    success=false
                                    warning ="Please Enter Subject"
                                }else{

                                        onExecuteAction()
                                        success= true
                                        onDismiss()


                                }


                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1F)
                        ) {
                            Text(text = "Save")
                        }
                    }


                }
            }





    }
}