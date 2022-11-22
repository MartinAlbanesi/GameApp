package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

interface ScoreRepository {

    fun getScores(game: String): LiveData<List<ScoreEntity>>
    fun getAllScores(): LiveData<List<ScoreEntity>>

    suspend fun setScore(score: ScoreEntity)

    suspend fun deleteScore(score: ScoreEntity)


}