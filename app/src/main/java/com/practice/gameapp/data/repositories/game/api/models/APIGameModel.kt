package com.practice.gameapp.data.repositories.game.api.models

import com.google.gson.annotations.SerializedName

data class APIGameModel ( @SerializedName("id")        val id:Int,
                          @SerializedName("title")     val title:String,
                          @SerializedName("thumbnail") val thumbnail:String,  //minuatura
                          @SerializedName("short_description") val ShortDescription:String,
                          @SerializedName("game_url") val gameUrl:String, // posible cambio
                          @SerializedName("genre") val genre:String,
                          @SerializedName("platform") val platform:String ,
                          @SerializedName("publisher") val publisher:String,
                          @SerializedName("developer") val developer:String,
                          @SerializedName("release_date") val releaseDate:String,
                          @SerializedName("freetogame_profile_url") val freetogameProfileUrl:String
)
        /*"id": 540,
        "title": "Overwatch 2",
        "thumbnail": "https://www.freetogame.com/g/540/thumbnail.jpg",
        "short_description": "A hero-focused first-person team shooter from Blizzard Entertainment.",
        "game_url": "https://www.freetogame.com/open/overwatch-2",
        "genre": "Shooter",
        "platform": "PC (Windows)",
        "publisher": "Activision Blizzard",
        "developer": "Blizzard Entertainment",
        "release_date": "2022-10-04",
        "freetogame_profile_url"*/

