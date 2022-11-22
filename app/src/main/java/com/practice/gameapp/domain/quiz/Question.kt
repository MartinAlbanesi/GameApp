package com.practice.gameapp.domain.quiz

import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.domain.models.GameModel

interface Question {
    fun getText(gameModel: GameModel): String
    fun getField(gameModel: GameModel): String
}