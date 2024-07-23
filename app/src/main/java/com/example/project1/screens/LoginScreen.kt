package com.example.project1.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project1.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authViewModel.authState.value) {
        when(authState.value){
            is AuthViewModel.AuthState.Authenticated -> navController.navigate("home")
            is AuthViewModel.AuthState.Error -> Toast.makeText(context, (authState.value as AuthViewModel.AuthState.Error).error, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login", fontFamily = FontFamily.Serif,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(20.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            singleLine = true,
            label = {
                Text(text = "Email", fontFamily = FontFamily.Serif)
            },
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            singleLine = true,
            label = {
                Text(text = "Password", fontFamily = FontFamily.Serif)
            },
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            authViewModel.login(email, password)
        },
            enabled = authState.value != AuthViewModel.AuthState.OnLoading,
        ) {
            Text(text = "Login")
        }

        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text(text = "Don't have a account, Sign Up")
        }

    }
}