package com.practice.gameapp.domain.quiz

import com.practice.gameapp.domain.models.GameModel

class QuestionReleaseDate: Question {

    override fun getText(gameModel: GameModel): String {
        return "Which year was ${gameModel.title} released?"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.releaseDate
    }

}

class QuestionTitleReleaseDate: Question {
    override fun getText(gameModel: GameModel): String {
        return "In which date did ${gameModel.releaseDate} released?"
    }

    override fun getField(gameModel: GameModel): String {
        return gameModel.title
    }

}

class QuestionGenre: Question {

    override fun getText(gameModel: GameModel): String {
        return "What is the genre of ${gameModel.title}?"
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