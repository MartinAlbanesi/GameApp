package com.practice.gameapp.data.repositories.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.gameapp.data.repositories.database.entities.GameEntity

interface GameDao {

    @Query("SELECT * FROM games_table LIMIT :limit ")
    fun getGames(game: String,limit:Int):LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGame(game: GameEntity)

    @Delete
    fun deleteGame(game: GameEntity)
}