package com.eduardoomarson.todolistfirebase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: String, // Adicionado para relacionar tarefa ao usuário
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
)