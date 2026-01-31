package com.eduardoomarson.todolistfirebase.ui.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardoomarson.todolistfirebase.data.TodoRepository
import com.eduardoomarson.todolistfirebase.navigation.AddEditRoute
import com.eduardoomarson.todolistfirebase.ui.UiEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: TodoRepository,
    private val userId: String, // Alteração CLAUDE
) : ViewModel() {

    val todos = repository.getAll(userId) // Linha alterada
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue= emptyList(),
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent){
        when (event){
            is ListEvent.Delete -> {
                delete(event.id)
            }
            is ListEvent.CompleteChanged -> {
                completeChanged(event.id, event.isCompleted)
            }
            is ListEvent.AddEdit -> {
                viewModelScope.launch{
                    _uiEvent.send(UiEvent.Navigate(AddEditRoute(event.id)))
                }
            }
        }
    }

    private fun delete(id: Long){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun completeChanged(id: Long, isCompleted: Boolean){
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }
}