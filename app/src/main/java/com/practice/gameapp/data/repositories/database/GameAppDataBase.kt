package com.practice.gameapp.data.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.gameapp.data.repositories.database.dao.GameDao
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity

@Database(entities = [
    ScoreEntity::class,GameEntity::class
],
    version = 2
)
abstract class GameAppDataBase : RoomDatabase() {

    abstract fun getScoreDao():ScoreDao
    abstract fun getGameDao(): GameDao
}
