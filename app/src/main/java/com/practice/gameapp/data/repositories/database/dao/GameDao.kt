package com.practice.gameapp.data.repositories.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practice.gameapp.data.repositories.database.entities.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM games_table ORDER BY RANDOM() LIMIT :limit ")
    fun getGames(limit: Int): LiveData<List<GameEntity>>

    @Query("select * from games_table")
    fun getAllGames(): LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setGame(game: GameEntity)

    @Delete
    suspend fun deleteGame(game: GameEntity)
}