package com.practice.gameapp.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import kotlinx.coroutines.launch

class HomeViewModel(private val gameRepository: GameRepository) : ViewModel() {

    val gameTitle = MutableLiveData("fill")
    val gameId = MutableLiveData("fill")
    var beerImageUrl = MutableLiveData(Uri.parse("https://images.punkapi.com/v2/192.png"))
    var allGamesList = MutableLiveData(listOf(GameModel(0, "fill", "fill")))

    suspend fun fillGamesList() {
        viewModelScope.launch {
            allGamesList.value = gameRepository.getGames()
        }
    }

    suspend fun getRandomGameImage() {
        beerImageUrl.value = Uri.parse(gameRepository.getRandomGame().thumbnail.toString())
    }


}