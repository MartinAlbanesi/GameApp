package com.practice.gameapp.data.repositories.network.game

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.practice.gameapp.data.repositories.models.APIGameModel
import com.practice.gameapp.domain.models.ErrorMessage
import retrofit2.Response

class GameAPIClient (
    private val gameAPI: GameAPI
) : GameClient {

    //Fetches all the games from Response and returns a list
    override suspend fun fetchGames(): List<APIGameModel> {
        val gameAPIResponse = gameAPI.getGames()
        if (!gameAPIResponse.isSuccessful) {
            throw Exception(handleUnsuccessfulResponse(gameAPIResponse).statusMessage)
        }
        return gameAPIResponse.body() ?: emptyList()
    }

    private fun <T> handleUnsuccessfulResponse(response: Response<T>): ErrorMessage {
        return try {
            Gson().fromJson(response.errorBody()!!.string(), ErrorMessage::class.java)
        } catch (je: JsonSyntaxException) {
            ErrorMessage(500, "Unknown")
        }
    }
}