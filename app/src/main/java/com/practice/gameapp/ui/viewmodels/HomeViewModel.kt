package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import kotlinx.coroutines.launch

class HomeViewModel(private val gameRepository: GameRepository) : ViewModel() {

    var randomGame: MutableLiveData<GameModel> =
        MutableLiveData(GameModel(0, "fillRandom", "fillRandom"))
    var allGamesList = MutableLiveData(listOf(GameModel(0, "fill", "fill")))

    ////Fills the MutableLiveData value with the game list from repository
    suspend fun fillGamesList() {
        viewModelScope.launch {
            gameRepository.getGames()
                .collect { gamesList ->
                    allGamesList.value = gamesList
                }
        }
    }

    //Fills the MutableLiveData value with a random game from repository
    suspend fun fillRandomGame() {
        viewModelScope.launch {
            gameRepository.getRandomGame()
                .collect { random ->
                    randomGame.value = random
                }
        }
    }


}