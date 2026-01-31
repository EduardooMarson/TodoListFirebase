package com.eduardoomarson.todolistfirebase.authentication

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    object PasswordResetSend : AuthState()
    data class Error(val message : String) : AuthState()
}