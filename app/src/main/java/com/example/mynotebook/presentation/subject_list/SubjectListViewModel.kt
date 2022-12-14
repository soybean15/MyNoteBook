package com.example.mynotebook.presentation.subject_list

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb

import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.mynotebook.BaseApplication
import com.example.mynotebook.R
import com.example.mynotebook.data.data_source.AppDatabase
import com.example.mynotebook.data.repository.AppRepositoryImpl
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.AppRepository
import com.example.mynotebook.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject



@HiltViewModel
class SubjectListViewModel
 @Inject constructor(
    private val  repository: AppRepository,
) :ViewModel(){




   // var subjects by mutableStateOf(emptyList<Subject>())
    var subjects = mutableStateOf(emptyList<Subject>())
    var subject by mutableStateOf(Subject("",0,0))

    private val colors = listOf(
        green_800, purple_700, yellow_900, orange_900, pink_800, brown_900, teal_800,
        red_700
    )
    init {
        getSubjects()
    }


    val text = mutableStateOf("")
    val loading = mutableStateOf(false)


    fun getSubjects() {
        viewModelScope.launch {
            repository.subjects().collect { response ->
                subjects.value = response
            }
        }
    }
    fun findSubjects() {


        Log.d("checkingText",text.value)
        viewModelScope.launch {
            subjects.value = listOf()

            repository.findSubject(text.value).collect { response ->
                Log.d("checkingTextValue","$response")
                if (response.isEmpty()){
                    getSubjects()
                }
                subjects.value = response
            }

        }


    }

    fun addSubject() {

            viewModelScope.launch(IO) {


            val subject = Subject(text.value,System.currentTimeMillis(), colors.random().toArgb())
            repository.insertSubject(subject)
            text.value= ""
        }

    }

    fun onTextChange(query:String){
        this.text.value= query
    }



    fun toTimeDateString(longDate:Long): String {
        return Date(longDate).toString()
    }
}