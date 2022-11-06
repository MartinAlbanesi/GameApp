package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.dao.GameDao
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import javax.inject.Inject

class GameDBRepositoryImpl @Inject constructor(
private val gameDao: GameDao
) : GameDBRepository{
    override suspend fun getGames(game: String, limit: Int): LiveData<List<GameEntity>> {
       return gameDao.getGames(game, limit)
    }

    override suspend fun setGame(game: GameEntity) {
       gameDao.setGame(game)
    }

    override suspend fun deleteGame(game: GameEntity) {
        gameDao.deleteGame(game)
    }
}