package com.practice.gameapp.domain.quiz

import com.practice.gameapp.data.repositories.database.entities.GameEntity

abstract class Question {
    abstract fun getText(gameEntity: GameEntity): String
    abstract fun getField(gameEntity: GameEntity): String
}