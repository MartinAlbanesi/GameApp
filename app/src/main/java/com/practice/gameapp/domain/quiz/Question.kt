package com.practice.gameapp.domain.quiz

import com.practice.gameapp.domain.models.GameModel

interface Question {
    fun getText(gameModel: GameModel): String
    fun getField(gameModel: GameModel): String
}