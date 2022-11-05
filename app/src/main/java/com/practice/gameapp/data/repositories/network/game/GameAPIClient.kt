package com.practice.gameapp.data.repositories.network.game

import com.practice.gameapp.data.repositories.models.APIGameModel
import javax.inject.Inject

class GameAPIClient @Inject constructor(
    //gameAPIProvider: GameAPIProvider
    private val gameAPI: GameAPI
) : GameClient {
    //private val gameAPI: GameAPI = gameAPIProvider.getAPI()

    //Fetches all the games from Response and returns a list
    override suspend fun fetchGames(): List<APIGameModel> {
        val gameAPIResponse = gameAPI.getGames()
        if (!gameAPIResponse.isSuccessful) {
            //Exception handling
        }
        return gameAPIResponse.body() ?: emptyList()
    }
}