package com.example.mynotebook.presentation.subject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.model.SubjectWithTopics
import com.example.mynotebook.domain.model.Topic
import com.example.mynotebook.domain.repository.ItemRepository
import com.example.mynotebook.domain.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubjectViewModel
@Inject constructor(
    private val repository: ItemRepository
):ViewModel (){

    var subject = mutableStateOf(Subject("",0,0,false))
    val subjectWithTopics = mutableStateOf(
        SubjectWithTopics(subject = Subject("",0,0,false),
        emptyList()

    ))

    private val _expandAddScreen = MutableStateFlow(false)
    val expandAddScreen: StateFlow<Boolean> = _expandAddScreen.asStateFlow()


    init {

    }


//    fun getSubject(id: Int) {
//        viewModelScope.launch {
//            repository.getSubject(id).collect{res ->
//                subject.value = res
//            }
//        }
//    }

     fun getSubjectWithTopics(id: Int) {
        viewModelScope.launch {
            repository.getSubjectWithTopics(id).collect(){ response ->
                subjectWithTopics.value = response
                subject.value = subjectWithTopics.value.subject
            }
        }
    }


    fun insertTopic(topic: Topic){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTopic(topic)
        }
    }

    fun expandAddScreen(){
        _expandAddScreen.value = true
    }
    fun onDismissAddScreen(){
        _expandAddScreen.value = false
    }
}