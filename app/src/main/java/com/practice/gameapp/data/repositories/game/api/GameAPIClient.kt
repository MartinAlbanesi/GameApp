package com.practice.gameapp.data.repositories.game.api

import com.practice.gameapp.data.repositories.game.api.models.APIGameModel
import javax.inject.Inject

class GameAPIClient @Inject constructor(
    private val gameAPI: GameAPI/*: GameAPIProvider*/
) : GameClient {
    //private val gameAPI: GameAPI = gameAPIProvider.getAPI()

    override suspend fun fetchGame(): List<APIGameModel> {
        val gameAPIResponse = gameAPI.getGames()
        if (!gameAPIResponse.isSuccessful) {
            //Exception handling
        }
        return gameAPIResponse.body() ?: emptyList()
    }
}