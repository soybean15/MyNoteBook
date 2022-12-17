package com.example.mynotebook.presentation.subject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mynotebook.BaseApplication
import com.example.mynotebook.presentation.subject.component.BottomContainer
import com.example.mynotebook.presentation.subject.component.SubjectToolbar
import com.example.mynotebook.ui.theme.MyNoteBookTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubjectFragment:Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: SubjectViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("subject_id")?.let { recipeId ->
            viewModel.getSubject(recipeId)

        }
    }



    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent{

                val scaffoldState = rememberScaffoldState()



                val subject = viewModel.subject.value
                MyNoteBookTheme(darkTheme = application.isDark.value) {

                    Scaffold(
                        topBar = {
                            SubjectToolbar(
                                subject = subject,
                                navController =  findNavController()
                            )
                        },
                        bottomBar = {
                            BottomContainer()
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.background),

                            ) {

                        }

                    }

                }
                

            }


        }
    }
}