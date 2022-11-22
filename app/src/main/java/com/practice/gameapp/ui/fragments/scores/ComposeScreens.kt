package com.practice.gameapp.ui.fragments.scores

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.LiveData
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

/**
 * Dialog that shows up to enter your score and name when the game finishes
 * @param score The game score
 * @param state Message if the game ended because time ended or because wrong answer
 * @param onClick function that excecutes when save button is clicked
 */

@Composable
fun DialogScore(
    score: Int,
    state: String,
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
                    text = stringResource(id = R.string.you_lose),
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.you_score) + score,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    fontSize = 16.sp
                )
                AnimatedVisibility(
                    visible = showError,
                    enter = expandVertically(
                        animationSpec = tween(1500),
                        expandFrom = Alignment.Bottom
                    ),
                ) {
                    Text(text = stringResource(id = R.string.text_error_length), color = Color.Red)
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.insert_name),
                            color = Color(0xFFFFFFFF),
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color(0xff7395d9)
                    ),
                    isError = showError,
                    maxLines = 1,
                    singleLine = true,
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
                        text = stringResource(id = R.string.save_text),
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun Scores(
    scoresGame: LiveData<List<ScoreEntity>>,
    onClickErased: (ScoreEntity) -> Unit
) {
    val scores by scoresGame.observeAsState(arrayListOf())

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
                Message(text = stringResource(id = R.string.name), Modifier.weight(2f))
                Message(text = stringResource(id = R.string.score_text), Modifier.weight(1f))
                Message(text = stringResource(id = R.string.date), Modifier.weight(2f))
                Message(text = "", Modifier.weight(0.5f))
            }
        }

        items(count) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Message(text = (it + 1).toString(), Modifier.weight(0.5f))
                Message(text = "${scores[it].name}", Modifier.weight(2f))
                Message(text = "${scores[it].score}", Modifier.weight(1f))
                Message(text = "${scores[it].date}", Modifier.weight(2f))
                IconButton(
                    onClick = { onClickErased(scores[it]) },
                    Modifier
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .size(26.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        Modifier.size(20.dp),
                        tint = Color.Red
                    )
                }
            }
        }

    }
}

@Composable
private fun Message(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp)),
        color = Color.White,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}