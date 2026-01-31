package com.eduardoomarson.todolistfirebase.data

import com.eduardoomarson.todolistfirebase.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insert(title: String, description: String?, userId: String, id: Long? = null)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean)

    suspend fun delete(id: Long)

    fun getAll(userId: String): Flow<List<Todo>>

    suspend fun getBy(id: Long) : Todo?
}