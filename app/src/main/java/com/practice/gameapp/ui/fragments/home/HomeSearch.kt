package com.practice.gameapp.ui.fragments.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.practice.gameapp.domain.models.GameModel

@Composable
fun HomeSearchGame(
    gameList: List<GameModel>,
    textGame: String
) {

    val gameListSize = gameList.size

    Column()
    {
//        Text(text = textGame, color = Color.White)
        gameList.forEach {
            if (it.title.startsWith(textGame[0].uppercaseChar())) {
                Text(text = it.title, color = Color.White)
            }
        }
    }
}