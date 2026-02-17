package com.example.myapplication55.presentation.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.uikit.ProjectCard
import com.example.uikit.ui.theme.Title2

@Composable
fun ProjectList(navController: NavHostController,viewModel: AuthViewModel){
    LaunchedEffect(Unit) { viewModel.loadUserProjects() }
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Проекты", style = Title2.titleMedium)
            TextButton(onClick = {navController.navigate(route = "createProject")},
                modifier = Modifier.size(40.dp)) {Text("+", style = Title2.titleMedium)}}
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            items(viewModel.userProjects) { project ->
                ProjectCard(project = project,
                    daysPassed = viewModel.calculateDaysPassed(project.dateStart),
                    onOpen={navController.navigate("create_project?projectId=${project.id}")}
                )
            }
        }
    }
}