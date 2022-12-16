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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.work.Operation.State.SUCCESS

@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onTextChange:(String)->Unit,
    onExecuteAdd:()->Unit,
    text:String,



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
//        if (success){
//            onDismiss()
//        }else{
//            warning = "Please input Subject"
//        }


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
                    .background(Color.White)
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MaterialTheme.colors.primary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {


                }

                OutlinedTextField(
                    value = text,
                    label = { Text(text = "Enter Subject") },
                    onValueChange = {
                        onTextChange(it)
                        if(it.isNotEmpty()){
                            warning = ""

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                if(!success){
                    Text(
                        text =warning,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)

                        )
                }


                Row(Modifier.padding(top = 10.dp)) {
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
                                onExecuteAdd()
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