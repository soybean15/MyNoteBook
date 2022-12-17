package com.example.mynotebook.presentation.subject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubjectViewModel
@Inject constructor(
    private val repository: AppRepository
):ViewModel (){

    var subject = mutableStateOf(Subject("",0,0,false))

    init {

    }


    fun getSubject(id: Int) {
        viewModelScope.launch {
            repository.getSubject(id).collect{res ->
                subject.value = res
            }
        }
    }
}