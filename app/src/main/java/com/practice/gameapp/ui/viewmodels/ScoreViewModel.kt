package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.data.repositories.database.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    private val _scoresVS: LiveData<List<ScoreEntity>> = scoreRepository.getScores("vs")
    val scoresVS: LiveData<List<ScoreEntity>> = _scoresVS

    private val _scoresQuiz: LiveData<List<ScoreEntity>> = scoreRepository.getScores("quiz")
    val scoresQuiz: LiveData<List<ScoreEntity>> = _scoresQuiz

    private val _game = MutableLiveData<String>("vs")
    val game: LiveData<String> = _game


    fun setScore(score: ScoreEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.setScore(score)
        }
    }

    fun searchGame(gameName: String) {
        _game.value = gameName
    }

    fun deleteScore(scoreEntity: ScoreEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.deleteScore(scoreEntity)
        }
    }


}