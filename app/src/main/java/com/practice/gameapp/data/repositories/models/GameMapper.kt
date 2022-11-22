package com.practice.gameapp.data.repositories.models

import com.practice.gameapp.domain.models.GameModel

fun APIGameModel.toGame() = GameModel(
    id = id,
    title = title,
    platform = platform,
    genre = genre,
    short_description = ShortDescription,
    thumbnail = thumbnail,
    releaseDate = releaseDate,
    developer = developer
)