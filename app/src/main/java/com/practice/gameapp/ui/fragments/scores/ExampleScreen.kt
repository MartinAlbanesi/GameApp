package com.practice.gameapp.ui.fragments.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.practice.gameapp.ui.viewmodels.DashboardViewModel

@Preview
@Composable
fun DashBoardScreen(dashboardViewModel: DashboardViewModel) {

    val scores by dashboardViewModel.scores.observeAsState()

    Text(text = "Algo", color = Color.White, modifier = Modifier.fillMaxSize())
}