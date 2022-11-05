package com.practice.gameapp.data.repositories.database.repository

import androidx.lifecycle.LiveData
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val scoreDao: ScoreDao
) : ScoreRepository {
    override suspend fun getAllScores(game: String): LiveData<List<ScoreEntity>> {
        return scoreDao.getAllScores(game)
    }

    override suspend fun setScore(score: ScoreEntity) {
        scoreDao.setScore(score)
    }

    override suspend fun deleteScore(score: ScoreEntity) {
        scoreDao.deleteScore(score)
    }

}