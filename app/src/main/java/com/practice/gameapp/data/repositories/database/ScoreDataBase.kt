package com.practice.gameapp.data.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

@Database(entities = [
    ScoreEntity::class
],
    version = 1
)
abstract class ScoreDataBase : RoomDatabase() {

    abstract fun getScoreDao():ScoreDao
}