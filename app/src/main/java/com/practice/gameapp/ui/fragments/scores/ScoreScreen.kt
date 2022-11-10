package com.practice.gameapp.ui.fragments.scores

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.ui.viewmodels.score.ScoreViewModel

@Composable
fun DialogScore(
    score: Int,
    onClick: () -> Unit,
) {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var showError by rememberSaveable {
        mutableStateOf(false)
    }

    var showDialog by rememberSaveable {
        mutableStateOf(true)
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x4FFFFFFF)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You score: $score",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(
                    visible = showError,
                    enter = expandIn(animationSpec = tween(1500)),
                ) {
                    Text(text = "No empty field pls :c", color = Color.Red)
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = "Insert name",
                            color = Color(0xFF000000)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color(0xff7395d9)
                    ),
                    isError = showError,
                )
                Button(
                    onClick = {
                        if (name.isNotEmpty() && name.length >= 3) {
                            showDialog = false
                            onClick()
                        }
                        showError = name.isEmpty() || name.length < 3
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff7395d9)
                    )
                ) {
                    Text(text = "Save", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Scores(
    scoreViewModel: ScoreViewModel = hiltViewModel()
) {
    val scores by scoreViewModel.scores.observeAsState(arrayListOf())

    val scorex = ScoreEntity(0, "a", 1, "a", "vs")

    val count = scores.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {

            Button(onClick = {
                scoreViewModel.setScore(scorex)
            }) {
                Text(text = "Set")
            }
        }

        items(count) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "${scores[it].id}",
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = "${scores[it].date}",
                    color = Color.White
                )
                Divider()
            }
        }

    }


}