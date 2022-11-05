package com.practice.gameapp.domain.models

import android.net.Uri

data class GameModel(
    val title: String,
    val platform: String,
    val genre: String,
    val short_description: String,
    val thumbnail: String
)