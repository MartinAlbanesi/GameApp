package com.practice.gameapp.ui.fragments.scores

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.ui.viewmodels.ScoreViewModel

@Composable
fun DashBoardScreen(scoreViewModel: ScoreViewModel) {

    val scores by scoreViewModel.scores.observeAsState(initial = listOf(ScoreEntity(0, "", 1, "", "vs")))

    Column() {
        Button(onClick = {
            scoreViewModel.setScore(ScoreEntity(0, "", 1, "", "vs"))
        }) {
            Text(text = "Set")
        }
        scores?.forEach{
            Text(text = "${it.id}", color = Color.White)
        }
    }


}