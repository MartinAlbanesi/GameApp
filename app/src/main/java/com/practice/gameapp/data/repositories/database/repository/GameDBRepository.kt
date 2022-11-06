package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.entities.GameEntity

interface GameDBRepository {
    suspend fun getAllGames(game: String,limit:Int): LiveData<List<GameEntity>>

    suspend fun setGame(game: GameEntity)

    suspend fun deleteGame(game: GameEntity)

}