package com.practice.gameapp.data.repositories.game.api

import com.practice.gameapp.data.repositories.game.api.models.APIGameModel

class GameAPIClient(gameAPIProvider: GameAPIProvider) :GameClient {
    private val GameAPI:GameAPI = gameAPIProvider.getAPI()

    override suspend fun fetchGame(): List<APIGameModel> {
        val gameAPIResponse = GameAPI.getGames()
        if(!gameAPIResponse.isSuccessful){
            throw Exception("manejo de error")
        }
        return gameAPIResponse.body()?: emptyList()
    }
}