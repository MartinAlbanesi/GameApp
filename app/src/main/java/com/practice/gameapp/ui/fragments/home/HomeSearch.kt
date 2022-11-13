package com.practice.gameapp.ui.fragments.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.practice.gameapp.domain.models.GameModel

@Composable
fun HomeSearchGame(
    gameList: List<GameModel>,
    textGame: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    )
    {
        gameList.forEach {
            if (it.title.startsWith(textGame[0].uppercaseChar())) {
                GameView(title = it.title, thumbnail = it.thumbnail)
            }
        }
    }
}

@Composable
fun GameView(
    title : String,
    thumbnail : String,
    modifier : Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = thumbnail
            ),
            contentDescription = null
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            text = title,
            color = Color.White,
            textAlign = TextAlign.Left
        )
    }
    Divider(thickness = 1.dp, color = Color.Black)
}