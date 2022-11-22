package com.practice.gameapp.data.repositories

import com.practice.gameapp.data.repositories.network.game.GameAPIClient
import com.practice.gameapp.data.repositories.network.game.GameClient
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.models.toGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class GameAPIRepository /*@Inject constructor*/(
    //private val gameClient: GameClient
    private val gameClient: GameClient
) : GameRepository {

    //Flow
    private val games: Flow<List<GameModel>> = flow {
        emit(gameClient.fetchGames().map { it.toGame() })
    }

    private val randomGame: Flow<GameModel> = flow {
        emit(gameClient.fetchGames().shuffled()[0].toGame())
    }
/*
    override suspend fun getFourRandomGames(): Flow<MutableList<GameModel>> {
        var listOfFour: MutableList<GameModel>
        for(random in games){
            listOfFour.add(random)
        }
        return games
    }
*/
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
