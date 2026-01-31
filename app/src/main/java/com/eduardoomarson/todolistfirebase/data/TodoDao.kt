package com.eduardoomarson.todolistfirebase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TodoEntity)

    @Delete
    suspend fun delete(entity: TodoEntity)

    /* Sugestão CLAUDE:
    PROMPT: Considerando que no TodoEntity tenho userId e cada usuário deve visualizar apenas as
    próprias tarefas, sei que devo alterar a função getAll passando como parâmetro userId, de acordo
    com o código em anexo, devo realizar mais alterações em TodoDao?
     */
    @Query("SELECT * from todos WHERE userId = :userId ORDER BY id DESC")
    fun getAll(userId: String): Flow<List<TodoEntity>>

    /* Fim Sugestão CLAUDE */

    @Query("SELECT * from todos WHERE id = :id")
    suspend fun getBy(id: Long) : TodoEntity?
}