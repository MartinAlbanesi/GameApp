package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.data.repositories.database.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    private val _scores = MutableLiveData<List<ScoreEntity>>()
    val scores : LiveData<List<ScoreEntity>> = _scores

    init {
        viewModelScope.launch {
            _scores.value = scoreRepository.getAllScores("vs").value
        }
    }

    fun insert(){
        val algo = ScoreEntity(0,"algo",1,"algo","algo")
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text
}
