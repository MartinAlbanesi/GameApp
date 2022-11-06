package com.practice.gameapp.ui.fragments.scores

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DashBoardScreen(
    //scoreViewModel: ScoreViewModel = hiltViewModel()
) {

    val scoreViewModel : ScoreViewModel by hiltViewModel()

    val scores by scoreViewModel.scores.observeAsState()

    val coroutineScope = rememberCoroutineScope()

    val scorex = ScoreEntity(0, "a", 1, "a", "vs")

    Column() {
        Button(onClick = {
            scoreViewModel.setScore(scorex)
//            coroutineScope.launch {
//                withContext(Dispatchers.IO) {
//                }
//            }
        }) {
            Text(text = "Set")
        }
        scores?.forEach {
            Text(text = "${it.id}", color = Color.White)
        }
    }


}