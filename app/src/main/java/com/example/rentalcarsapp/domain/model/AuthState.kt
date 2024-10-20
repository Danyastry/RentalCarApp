package com.example.rentalcarsapp.domain.model

sealed class AuthState {
    object Authenticated : AuthState()
    object ResetPassword : AuthState()
    object Registered : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}