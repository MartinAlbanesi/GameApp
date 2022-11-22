package com.practice.gameapp.data.repositories.models

import com.google.gson.annotations.SerializedName

data class APIGameModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,  //minuatura
    @SerializedName("short_description") val ShortDescription: String,
    @SerializedName("game_url") val gameUrl: String, // posible cambio
    @SerializedName("genre") val genre: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("developer") val developer: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("freetogame_profile_url") val freetogameProfileUrl: String
)

