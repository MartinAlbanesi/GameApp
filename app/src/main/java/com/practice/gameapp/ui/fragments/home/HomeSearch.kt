package com.practice.gameapp.ui.fragments.home

import android.R
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            if (it.title.uppercase().startsWith(textGame.uppercase())) {
                GameView(it)
            }
        }
    }
}

@Composable
fun GameView(
    gameModel: GameModel,
    modifier: Modifier = Modifier
) {

    var isSize by rememberSaveable {
        mutableStateOf(false)
    }

    val size by animateDpAsState(
        targetValue = if (isSize) 200.dp else 56.dp,
        animationSpec = tween(300)
    )
    val sizeImage by animateDpAsState(if (isSize) 175.dp else 100.dp, animationSpec = tween(300))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(size)
            .clickable { isSize = !isSize }
    ) {

        Column() {

            AsyncImage(
                model = gameModel.thumbnail,
                contentDescription = null,
                modifier = modifier.size(sizeImage),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.ic_delete)
            )

            if (isSize) {
                Text(
                    text = gameModel.platform,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
        Column() {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                text = gameModel.title,
                color = Color.White,
                textAlign = TextAlign.Left
            )
            if (isSize) {
                Divider(thickness = 1.dp, color = Color.Black)
                Text(
                    modifier = Modifier
                        .height(125.dp)
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    text = gameModel.short_description,
                    textAlign = TextAlign.Left,
                    color = Color.White
                )
                Divider(thickness = 1.dp, color = Color.Black)
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp),
                    text = gameModel.genre,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
    Divider(thickness = 2.dp, color = Color.Black)
}