package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.entities.GameEntity

interface GameDBRepository {

    fun getAllGames2():LiveData<List<GameEntity>>

    fun getAllGames(game: String,limit:Int): LiveData<List<GameEntity>>

    suspend fun setGame(game: GameEntity)

    suspend fun deleteGame(game: GameEntity)

}