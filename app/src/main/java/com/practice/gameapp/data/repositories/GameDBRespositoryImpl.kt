package com.practice.gameapp.data.repositories

import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.domain.models.GameModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameDBRespositoryImpl @Inject constructor(

) : GameRepository {
    override suspend fun getGames(): Flow<List<GameModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomGame(): Flow<GameModel> {
        TODO("Not yet implemented")
    }
}