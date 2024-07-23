package com.example.project1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project1.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        topBar = {
            TopBar(navController, authViewModel,
                expand = { isExpanded = !isExpanded })
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Home Screen")
            }
            if (isExpanded) {
                ElevatedCard(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 100.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 5.dp
                    ),
                    colors =
                    CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            authViewModel.logout()
                            navController.navigate("login")
                        }) {
                            Icon(
                                Icons.AutoMirrored.Rounded.Logout,
                                contentDescription = "More Icon"
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Logout")
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(navController: NavHostController, authViewModel: AuthViewModel, expand: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Notes", fontFamily = FontFamily.Serif, fontSize = 24.sp)
        IconButton(onClick = {
            expand()
        }) {
            Icon(Icons.Rounded.MoreVert, contentDescription = "More Icon")
        }

    }
}

