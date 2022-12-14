package com.example.mynotebook.presentation.subject_list

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb

import androidx.lifecycle.*
import com.example.mynotebook.domain.model.Subject
import com.example.mynotebook.domain.repository.ItemRepository
import com.example.mynotebook.domain.repository.SubjectRepository
import com.example.mynotebook.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject



@HiltViewModel
class SubjectListViewModel
 @Inject constructor(
     private val  repository: SubjectRepository,
) :ViewModel(){




    var favoriteSubjects = mutableStateOf(emptyList<Subject>())
    var subjects = mutableStateOf(emptyList<Subject>())



    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy ")
    val currentDate = mutableStateOf(  current.format(formatter))



    private val colors = listOf(
        green_800, purple_700, yellow_900, orange_900, pink_800, brown_900, teal_800,
        red_700
    )

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()


    private val _onAdd = MutableStateFlow(false)
    val onAdd: StateFlow<Boolean> = _onAdd.asStateFlow()

    var subject = mutableStateOf(Subject("",0,0,false))




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


                    val _subject = Subject(subjectToAdd.value,System.currentTimeMillis(), colors.random().toArgb(),false)
                    repository.insertSubject(_subject)


                    subjectToAdd.value= ""
                    subject.value =Subject("",0,0,false)


            }

    }

    fun deleteSubject(){


            viewModelScope.launch(IO) {
                repository.deleteSubject(subject.value.id)


            }
            _showDialog.value = false



    }

    fun updateSubject() {
        subject.value.timeStamp = System.currentTimeMillis()
        subject.value.name = subjectToAdd.value
        viewModelScope.launch(IO) {
            repository.updateSubject(subject.value)

            subjectToAdd.value= ""
            subject.value =Subject("",0,0,false)
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



    fun onSetInitialText(text:String){
        this.subjectToAdd.value = text

    }


    fun toTimeDateString(longDate:Long): String {
        return Date(longDate).toString()
    }


    fun onOpenDialogClicked(subject: Subject) {
       this.subject.value = subject
        _showDialog.value = true
    }



    fun onDialogDismiss() {
        _showDialog.value = false
        _onAdd .value = false
    }

    fun onOpenCustomDialog_Add (){
        _showAddDialog.value = true
        _onAdd.value = true
    }

    fun onOpenCustomDialog_Edit(subject: Subject){
        _showAddDialog.value = true
        this.subject.value = subject
    }
    fun onDismissCustomDialog (){
        _showAddDialog.value = false
        _onAdd .value = false

    }
}