package com.practice.gameapp.domain.quiz

object Questions {

    private val questionsList = listOf(QuestionGenre(),QuestionDeveloper(),QuestionReleaseDate(),QuestionTitleReleaseDate())

    fun getRandomQuestion(): Question{

        return questionsList[(1..questionsList.size).random() - 1]
    }
}