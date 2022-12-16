package com.example.mynotebook.presentation.subject_list

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynotebook.BaseApplication
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.presentation.subject_list.components.AppDrawer
import com.example.mynotebook.presentation.subject_list.components.CustomAlertDialog
import com.example.mynotebook.presentation.subject_list.components.SearchBar
import com.example.mynotebook.presentation.subject_list.components.SubjectItem
import com.example.mynotebook.ui.theme.MyNoteBookTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SubjectListFragment :Fragment(){

    @Inject
    lateinit var application: BaseApplication

    private lateinit var viewModel: SubjectListViewModel


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(SubjectListViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent{

                val scaffoldState = rememberScaffoldState()


                val query = viewModel.query.value
                val subjectStr = viewModel.subjectToAdd.value
                val subjects =viewModel.subjects.value
                val favorites = viewModel.favoriteSubjects.value

                val currentDate = viewModel.currentDate.value

                var showCustomDialog by remember {
                    mutableStateOf(false)
                }



                MyNoteBookTheme(darkTheme = application.isDark.value) {

                    Scaffold(
                        floatingActionButton  = {
                            FloatingActionButton(
                                onClick = { showCustomDialog = !showCustomDialog},
                                backgroundColor = MaterialTheme.colors.primary,


                                ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                            }
                        },
                        drawerContent = {
                            AppDrawer(
                                darkMode = application.isDark.value,
                                toggleLightTheme ={
                                    application.toggleLightTheme()
                                },
                                favorites,
                                currentDate = currentDate

                            )
                        },
                        scaffoldState = scaffoldState
                    ) {



                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.background),

                            ) {

                            SearchBar(
                                text = query,
                                onQueryChange = viewModel::onQueryChange,

                                onExecuteSearch = viewModel::findSubjects
                            )



                            val selectedSubject = remember {
                                mutableStateOf(Subject("",0,0,false))
                            }








                            LazyColumn() {
                                itemsIndexed(
                                    items = subjects.sortedByDescending {
                                        it.timeStamp
                                    }
                                ) { _, subject ->

                                    SubjectItem(
                                        subject = subject,
                                        onConvertDateToString = viewModel::toTimeDateString,
                                        onAddToFavorites = {
                                            selectedSubject.value = subject
                                            viewModel.addToFavorites(subject)
                                        }
                                    , favorites = subject.favorites
                                    )


                                }

                            }


                        }





                        if (showCustomDialog){
                            CustomAlertDialog(
                                onDismiss = {
                                showCustomDialog = !showCustomDialog
                                 },
                                onTextChange = viewModel::onSubjectTextChange,
                                text = subjectStr,
                                onExecuteAdd =  viewModel::addSubject,

                            )


                        }


                    }



                }
            }


        }
    }
}

