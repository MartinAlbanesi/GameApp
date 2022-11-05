package com.practice.gameapp.data.repositories.network.game

import com.practice.gameapp.data.repositories.models.APIGameModel

interface GameClient {
    suspend fun fetchGames(): List<APIGameModel>
}