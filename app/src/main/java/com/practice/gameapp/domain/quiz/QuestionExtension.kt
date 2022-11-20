package com.practice.gameapp.domain.quiz

import com.practice.gameapp.domain.models.GameModel

class QuestionReleaseDate: Question {

    override fun getText(gameModel: GameModel): String {
        return "En que año fue lanzado ${gameModel.title}?"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.releaseDate
    }

}

class QuestionTitleReleaseDate: Question {
    override fun getText(gameModel: GameModel): String {
        return "Que juego salio en esta fecha ${gameModel.releaseDate}"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.title
    }

}

class QuestionGenre: Question {

    override fun getText(gameModel: GameModel): String {
        return "Cual es el género de ${gameModel.title}?"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.genre
    }

}

class QuestionDeveloper: Question {

    override fun getText(gameModel: GameModel): String {
        return "Which developer made ${gameModel.title}?"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.developer
    }

}