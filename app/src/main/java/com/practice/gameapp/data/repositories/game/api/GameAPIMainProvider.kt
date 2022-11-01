package com.practice.gameapp.data.repositories.game.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameAPIMainProvider : GameAPIProvider {
    override fun getAPI(): GameAPI {
        val serviceGetGamesByAPI = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return serviceGetGamesByAPI.create(GameAPI::class.java)
    }
}
