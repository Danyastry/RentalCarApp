package com.example.rentalcarsapp.VIewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentalcarsapp.Model.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private var _signUpEmail = MutableLiveData("")
    var signUpEmail: LiveData<String> = _signUpEmail

    private var _signUpPassword = MutableLiveData("")
    var signUpPassword: LiveData<String> = _signUpPassword

    private var _signInEmail = MutableLiveData("")
    var signInEmail: LiveData<String> = _signInEmail

    private var _signInPassword = MutableLiveData("")
    var signInPassword: LiveData<String> = _signInPassword

    private val _passwordLength = mutableStateOf(false)
    val passwordLength: State<Boolean> = _passwordLength

    private val _containsDigit = mutableStateOf(false)
    val containsDigit: State<Boolean> = _containsDigit

    private val _containsUpperCase = mutableStateOf(false)
    val containsUpperCase: State<Boolean> = _containsUpperCase

    init {
        checkAuth()
    }

    private fun checkAuth() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.postValue(AuthState.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _authState.postValue(AuthState.Authenticated)
                } else {
                    _authState.postValue(
                        AuthState.Error(
                            it.exception?.message ?: "Oops, something went wrong"
                        )
                    )
                }
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.postValue(AuthState.Loading)
            if (isValidEmail(email) && isPasswordValid(password)) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _authState.postValue(AuthState.Registered)
                        Log.d("Log.d", "Success SignUp")
                    } else {
                        Log.d("Log.d", "Error SignUp")
                        _authState.postValue(
                            AuthState.Error(
                                it.exception?.message ?: "Oops, something went wrong"
                            )
                        )
                    }
                }
            } else {
                Log.d("Log.d", "Error invalid")
                _authState.postValue(AuthState.Error(message = "Invalid email or password"))
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.postValue(AuthState.Loading)
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    _authState.postValue(AuthState.ResetPassword)
                } else {
                    _authState.postValue(
                        AuthState.Error(it.exception?.message ?: "Oops, something went wrong")
                    )
                }
            }
        }
    }

    fun updatePasswordSignUp(password: String) {
        _signUpPassword.value = password
        _passwordLength.value = password.length > 6
        _containsDigit.value = password.any { it.isDigit() }
        _containsUpperCase.value = password.any { it.isUpperCase() }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return emailRegex.matches(email)
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$".toRegex()
        return passwordRegex.matches(password)
    }

    fun updateSignUpEmail(email: String) {
        _signUpEmail.value = email
    }

    fun updateSignInEmail(email: String) {
        _signInEmail.value = email
    }

    fun updateSignInPassword(password: String) {
        _signInPassword.value = password
    }

}