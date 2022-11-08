package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

interface ScoreRepository {

    fun getAllScores(game: String): LiveData<List<ScoreEntity>>

    suspend fun setScore(score: ScoreEntity)

    suspend fun deleteScore(score: ScoreEntity)
}