package com.example.mynotebook.presentation.subject_list.components

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.example.mynotebook.domain.model.Subject


@Composable
fun ConfirmDialog(
    onDismiss:()->Unit,
    onConfirm:()->Unit,
    showDialog: Boolean,

    subject: Subject
){




    if (showDialog){
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = {
                  onConfirm()

                })
                { Text(text = "OK") }
            },
            dismissButton = {
                TextButton(onClick = {onDismiss()})
                { Text(text = "Cancel") }
            },
            title = { Text(text = "Delete Subject") },
            text = { Text(text = "Are you sure you want to delete ${subject.name}") }
        )

    }



}
