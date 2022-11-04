package com.practice.gameapp.data.repositories

import com.practice.gameapp.data.repositories.game.api.GameClient
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.models.toGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameAPIRepository(private val gameClient: GameClient) : GameRepository {

    //Flow
    private val games: Flow<List<GameModel>> = flow {
        emit(gameClient.fetchGames().map { it.toGame() })
    }
    private val randomGame: Flow<GameModel> = flow {
        emit(gameClient.fetchGames().shuffled()[0].toGame())
    }

    //Returns a flow emit with a list of games
    override suspend fun getGames(): Flow<List<GameModel>> {
        return games
    }

    //Returns a flow emit with a random game
    override suspend fun getRandomGame(): Flow<GameModel> {
        return randomGame
    }


}


/*
 fun prueba(): List<GameModel> {
     return gameClient.fetchGame().map {it.toGame()}
 }*/
