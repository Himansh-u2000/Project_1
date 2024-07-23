package com.example.project1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkUserStatus()
    }

    private fun checkUserStatus() {
        if (auth.currentUser != null) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.UnAuthenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Please fill all the fields")
            return
        }
        _authState.value = AuthState.OnLoading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }

            }
    }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Please fill all the fields")
            return
        }

        _authState.value = AuthState.OnLoading
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = AuthState.Authenticated
            } else {
                _authState.value =
                    AuthState.Error(task.exception?.message ?: "Something went wrong")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }


    sealed class AuthState {
        object Authenticated : AuthState()
        object UnAuthenticated : AuthState()
        object OnLoading : AuthState()
        data class Error(val error: String) : AuthState()

    }
}