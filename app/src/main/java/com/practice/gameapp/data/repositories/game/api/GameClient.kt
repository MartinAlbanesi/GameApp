package com.practice.gameapp.data.repositories.game.api

import com.practice.gameapp.data.repositories.game.api.models.APIGameModel

interface GameClient {
    suspend fun fetchGames(): List<APIGameModel>
}