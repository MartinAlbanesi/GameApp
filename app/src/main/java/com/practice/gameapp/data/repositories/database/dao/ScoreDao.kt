package com.practice.gameapp.data.repositories.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

@Dao
interface ScoreDao {

    @Query("select * from score_table where game = :game order by score desc")
    suspend fun getAllScores(game : String):LiveData<List<ScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setScore(score : ScoreEntity)

    @Delete
    suspend fun deleteScore(score : ScoreEntity)
}