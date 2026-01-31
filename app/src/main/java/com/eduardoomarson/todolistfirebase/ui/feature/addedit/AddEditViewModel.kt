package com.eduardoomarson.todolistfirebase.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardoomarson.todolistfirebase.data.TodoRepository
import com.eduardoomarson.todolistfirebase.ui.UiEvent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val id: Long? = null,
    private val repository: TodoRepository,
    private val userId: String,
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        id?.let{
            viewModelScope.launch {
                val todo = repository.getBy(it)
                title = todo?.title ?: ""
                description = todo?.description
            }
        }
    }

    fun onEvent(event: AddEditEvent){
        when(event){
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }
            is AddEditEvent.DescriptionChanged -> {
                description = event.description
            }
            AddEditEvent.Save -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo(){
        viewModelScope.launch{
            if(title.isBlank()){
                _uiEvent.send(UiEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }
            repository.insert(title, description, userId, id)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
}