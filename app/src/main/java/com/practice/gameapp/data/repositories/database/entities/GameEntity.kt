package com.practice.gameapp.data.repositories.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN


@Entity(tableName = "games_table")
data class GameEntity (

    @PrimaryKey (autoGenerate = true)
    val id:Int,
    @ColumnInfo(name ="title")
    val title: String,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "short_description")
    val short_description: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String
        )