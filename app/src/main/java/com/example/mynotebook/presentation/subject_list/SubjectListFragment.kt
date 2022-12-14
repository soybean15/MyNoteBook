package com.example.mynotebook.presentation.subject_list

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.mynotebook.presentation.subject_list.components.SearchBar
import com.example.mynotebook.presentation.subject_list.components.SubjectItem
import com.example.mynotebook.ui.theme.MyNoteBookTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SubjectListFragment :Fragment(){


    lateinit var viewModel: SubjectListViewModel


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


                val text = viewModel.text.value
                

                    




                MyNoteBookTheme(darkTheme = false) {

                    Scaffold(
                        floatingActionButton  = {
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                backgroundColor = MaterialTheme.colors.primary,


                                ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                            }
                        },
                        drawerContent = {}
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.background),

                            ) {

                            SearchBar(
                                text = text,
                                onQueryChange = viewModel::onTextChange,
                                onExecuteAdd = viewModel::addSubject,
                                onExecuteSearch = viewModel::findSubjects
                            )





                            val subjects =viewModel.subjects.value


                            LazyColumn() {
                                itemsIndexed(
                                    items = subjects
                                ) { _, subject ->
                                    SubjectItem(
                                        subject = subject,
                                        onConvertDateToString = viewModel::toTimeDateString
                                    )


                                }

                            }


                        }


                    }



                }
            }


        }
    }
}

