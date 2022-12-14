package com.example.mynotebook.presentation.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.mynotebook.ui.theme.MyNoteBookTheme
import dagger.hilt.android.AndroidEntryPoint


class SubjectFragment:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent{

                MyNoteBookTheme(darkTheme = false) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .background(MaterialTheme.colors.background),

                    ) {
                        Text(text = "SubjectFragment")
                    }

                }
                

            }


        }
    }
}