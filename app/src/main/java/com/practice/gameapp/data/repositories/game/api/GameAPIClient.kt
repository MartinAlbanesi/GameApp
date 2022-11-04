package com.practice.gameapp.data.repositories.game.api

import com.practice.gameapp.data.repositories.game.api.models.APIGameModel

class GameAPIClient(gameAPIProvider: GameAPIProvider) : GameClient {
    private val gameAPI: GameAPI = gameAPIProvider.getAPI()

    //Fetches all the games from Response and returns a list
    override suspend fun fetchGames(): List<APIGameModel> {
        val gameAPIResponse = gameAPI.getGames()
        if (!gameAPIResponse.isSuccessful) {
            //Exception handling
        }
        return gameAPIResponse.body() ?: emptyList()
    }
}