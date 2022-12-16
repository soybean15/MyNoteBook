package com.example.mynotebook.presentation.subject_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb

import androidx.lifecycle.*
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.AppRepository
import com.example.mynotebook.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject



@HiltViewModel
class SubjectListViewModel
 @Inject constructor(
    private val  repository: AppRepository,
) :ViewModel(){




    var favoriteSubjects = mutableStateOf(emptyList<Subject>())
    var subjects = mutableStateOf(emptyList<Subject>())
    var subject = mutableStateOf(Subject("",0,0,false))
        private set
    fun setSubject(_subject: Subject){
       subject.value= _subject
    }


    private val colors = listOf(
        green_800, purple_700, yellow_900, orange_900, pink_800, brown_900, teal_800,
        red_700
    )
    init {
        getSubjects()
        getFavorites()
    }


    val query = mutableStateOf("")

    var subjectToAdd = mutableStateOf("")


    private fun getSubjects() {
        viewModelScope.launch {
            repository.subjects().collect { response ->
                subjects.value = response
            }
        }
    }
    private fun getFavorites() {
        viewModelScope.launch {
            repository.getFavorites().collect { response ->
                favoriteSubjects.value = response
            }
        }
    }

    fun findSubjects() {

        viewModelScope.launch {
            subjects.value = listOf()

            repository.findSubject(query.value).collect { response ->

                if (response.isEmpty()){
                    getSubjects()
                }
                subjects.value = response
            }

        }


    }

    fun addSubject() {

            viewModelScope.launch(IO) {


                    val subject = Subject(subjectToAdd.value,System.currentTimeMillis(), colors.random().toArgb(),false)
                    repository.insertSubject(subject)


                    subjectToAdd.value= ""

        }

    }



    fun addToFavorites(_subject: Subject):Subject{


        _subject.favorites = !_subject.favorites
        viewModelScope.launch(IO) {
            repository.addToFavorites(_subject)


        }
        return _subject



    }

    fun onQueryChange(query:String){
        this.query.value= query
    }

    fun onSubjectTextChange(text:String){
        this.subjectToAdd.value = text
    }



    fun toTimeDateString(longDate:Long): String {
        return Date(longDate).toString()
    }
}