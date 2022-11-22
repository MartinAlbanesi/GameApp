package com.practice.gameapp.data.repositories

import com.practice.gameapp.domain.models.GameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getGames(): Flow<List<GameModel>>
    suspend fun getRandomGame(): Flow<GameModel>
    //suspend fun getFourRandomGames(): Flow<List<GameModel>>
}
