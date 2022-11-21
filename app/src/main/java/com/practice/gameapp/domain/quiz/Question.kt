package com.practice.gameapp.domain.quiz

import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.domain.models.GameModel

abstract class Question () {
    abstract fun getText(gameModel: GameModel): String
    abstract fun getField(gameModel: GameModel): String
}