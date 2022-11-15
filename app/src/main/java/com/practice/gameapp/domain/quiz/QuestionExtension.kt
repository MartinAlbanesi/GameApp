package com.practice.gameapp.domain.quiz

import com.practice.gameapp.data.repositories.database.entities.GameEntity

class QuestionReleaseDate: Question() {

    override fun getText(gameEntity: GameEntity): String {
        return "En que año fue lanzado ${gameEntity.title}?"
    }

    override fun getField(gameEntity: GameEntity): String {
        return gameEntity.release_date
    }

}

class QuestionPlatform: Question() {

    override fun getText(gameEntity: GameEntity): String {
        return "En qué plataforma se puede jugar ${gameEntity.title}?"
    }

    override fun getField(gameEntity: GameEntity): String {
        return gameEntity.platform
    }

}

class QuestionGenre: Question() {

    override fun getText(gameEntity: GameEntity): String {
        return "Cual es el género de ${gameEntity.title}?"
    }

    override fun getField(gameEntity: GameEntity): String {
        return gameEntity.genre
    }

}

class QuestionDeveloper: Question() {

    override fun getText(gameEntity: GameEntity): String {
        return "Which developer made ${gameEntity.title}?"
    }

    override fun getField(gameEntity: GameEntity): String {
        return gameEntity.developer
    }

}