package com.practice.gameapp.data.repositories

import com.practice.gameapp.domain.models.GameModel

interface GameRepository {

    suspend fun getGames(): List<GameModel>
    suspend fun getRandomGame(): GameModel
}
