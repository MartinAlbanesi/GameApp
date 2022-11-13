package com.practice.gameapp.ui.viewmodels.score

import android.util.Log
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

    private val _scores : LiveData<List<ScoreEntity>> = scoreRepository.getAllScores()
    val scores : LiveData<List<ScoreEntity>> = _scores

    fun setScore(score: ScoreEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreRepository.setScore(score)
        }
    }


}