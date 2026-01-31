package com.eduardoomarson.todolistfirebase.domain

data class Todo(
    val id: Long,
    val userId: String, // Alterei para estar condizente com TodoEntity.
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)

//fake objects

val todo1 = Todo(
    id= 1,
    userId = "teste@gmail.com",
    title = "Todo 1",
    description = "Descrição da primeira tarefa",
    isCompleted = false,
)

val todo2 = Todo(
    id= 2,
    userId = "teste@gmail.com",
    title = "Todo 2",
    description = "Descrição da segunda tarefa",
    isCompleted = true,
)

val todo3 = Todo(
    id= 3,
    userId = "teste@gmail.com",
    title = "Todo 3",
    description = "Descrição da terceira tarefa",
    isCompleted = false,
)

