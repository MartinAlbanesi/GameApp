package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import kotlinx.coroutines.launch

//@HiltViewModel
class HomeViewModel /*@Inject constructor*/(
    private val gameRepository: GameRepository
) : ViewModel() {

    var randomGame: MutableLiveData<GameModel> =
        MutableLiveData()
    var allGamesList: MutableLiveData<List<GameModel>> =
        MutableLiveData(listOf(GameModel("fill", "fill", "fill", "fill", "fill", 0, "", "")))

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