package com.practice.gameapp.ui.fragments.scores

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.gameapp.ui.viewmodels.score.ScoreViewModel

@Composable
fun DialogScore(
    score: Int,
    state : String,
    onClick: (String) -> Unit,
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
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xAB030303)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = state,
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "You score: $score",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    fontSize = 16.sp
                )
                AnimatedVisibility(
                    visible = showError,
                    enter = expandVertically(animationSpec = tween(1500), expandFrom = Alignment.Bottom ),
                ) {
                    Text(text = "No empty field pls :c", color = Color.Red)
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = "Insert name",
                            color = Color(0xFFFFFFFF),
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color(0xff7395d9)
                    ),
                    isError = showError,
                    maxLines = 1
                )
                Button(
                    onClick = {
                        if (name.isNotEmpty() && name.length >= 3) {
                            showDialog = false
                            onClick(name)
                        }
                        showError = name.isEmpty() || name.length < 3
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff7395d9)
                    ),
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "Save",
                        color = Color.White,
                    )
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

    val count = scores.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .background(Color(0x28FFFFFF))
            ) {
                Message(text = "", Modifier.weight(0.5f))
                Message(text = "Name", Modifier.weight(2f))
                Message(text = "Score", Modifier.weight(1f))
                Message(text = "Date", Modifier.weight(2f))
            }
        }

        items(count) {
            //Positions(scoreEntity = scores[it], position = it)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Message(text = (it+1).toString(), Modifier.weight(0.5f))
                Message(text = "${scores[it].name}", Modifier.weight(2f))
                Message(text = "${scores[it].score}", Modifier.weight(1f))
                Message(text = "${scores[it].date}", Modifier.weight(2f))
            }
        }

    }
}

@Composable
fun Message(
    text : String,
    modifier : Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp)),
        color = Color.White,
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
}