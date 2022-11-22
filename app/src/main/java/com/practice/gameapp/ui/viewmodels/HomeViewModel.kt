package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.ui.components.ErrorMessage
import kotlinx.coroutines.launch

//@HiltViewModel
class HomeViewModel /*@Inject constructor*/(
    private val gameRepository: GameRepository
) : ViewModel() {

    var randomGame: MutableLiveData<GameModel> =
        MutableLiveData()
    var allGamesList: MutableLiveData<List<GameModel>> =
        MutableLiveData(listOf(GameModel("...", "...", "...", "...", "...", 0, "...", "...")))
    var errorMessage = MutableLiveData(ErrorMessage(false, ""))

    ////Fills the MutableLiveData value with the game list from repository
    suspend fun fillGamesList() {
        viewModelScope.launch {
            gameRepository.getGames()
                .collect { gamesList ->
                    try{
                        allGamesList.value = gamesList
                    }catch (e: Exception) {
                        errorMessage.value = ErrorMessage(true, e.message)
                    }

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