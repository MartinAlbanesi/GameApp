package com.practice.gameapp.domain.models

import com.practice.gameapp.data.repositories.game.api.models.APIGameModel

fun APIGameModel.toGame() = GameModel(
    id = id,
    title = title,
    thumbnail = thumbnail
)