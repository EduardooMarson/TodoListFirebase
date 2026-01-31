package com.eduardoomarson.todolistfirebase.ui.feature.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eduardoomarson.todolistfirebase.authentication.AuthViewModel
import com.eduardoomarson.todolistfirebase.data.TodoDatabaseProvider
import com.eduardoomarson.todolistfirebase.data.TodoRepositoryImpl
import com.eduardoomarson.todolistfirebase.domain.Todo
import com.eduardoomarson.todolistfirebase.domain.todo1
import com.eduardoomarson.todolistfirebase.domain.todo2
import com.eduardoomarson.todolistfirebase.domain.todo3
import com.eduardoomarson.todolistfirebase.navigation.AddEditRoute
import com.eduardoomarson.todolistfirebase.ui.UiEvent
import com.eduardoomarson.todolistfirebase.ui.components.TodoItem
import com.eduardoomarson.todolistfirebase.ui.feature.addedit.AddEditViewModel
import com.eduardoomarson.todolistfirebase.ui.theme.TodoListTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
    navigateToLoginScreen: () -> Unit,
    authViewModel: AuthViewModel,
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.todoDao)
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "" // Sugestão CLAUDE

    val viewModel = viewModel<ListViewModel>{
        ListViewModel(repository = repository, userId = userId)
    }

    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when (uiEvent.route){
                        is AddEditRoute -> {
                            navigateToAddEditScreen(uiEvent.route.id)
                        }
                    }
                }
                UiEvent.NavigateBack -> {
                }
                is UiEvent.ShowSnackbar -> {
                }
            }
        }
    }

    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent,
        onLogout = { // Sugestão Claude
            authViewModel.signout()
            navigateToLoginScreen()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit,
    onLogout: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            /*Sugestão CLAUDE
            PROMPT: Gostaria de adicionar um botão de Logout na tela de tarefas, inicialmente pensei em outro
            Floating Button, poderia avaliar o código e propor melhorias?
             */
            TopAppBar(
                title = { Text("Minhas Tarefas") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Sair")
                    }
                }
            )
            /* Fim Sugestão CLAUDE */
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ListEvent.AddEdit(null))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(todos) { index, todo ->
                TodoItem(
                    todo = todo,
                    onCompletedChange = {
                        onEvent(ListEvent.CompleteChanged(todo.id, it))
                    },
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(todo.id))
                    },
                    onDeleteClick = {
                        onEvent(ListEvent.Delete(todo.id))
                    },
                )

                if (index < todos.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    TodoListTheme{
        ListContent(
            todos = listOf(
                todo1,
                todo2,
                todo3
            ),
            onEvent = {},
        )
    }
}