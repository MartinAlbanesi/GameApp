package com.practice.gameapp.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameRepository: GameRepository
):ViewModel() {

    val gameTitle = MutableLiveData("fill")
    val gameId = MutableLiveData("fill")
    var beerImageUrl = MutableLiveData(Uri.parse("https://images.punkapi.com/v2/192.png"))
    var allGamesList = MutableLiveData(listOf(GameModel(0, "fill", "fill")))

    suspend fun fillGamesList() {
        viewModelScope.launch {
            allGamesList.value = gameRepository.getGames()
//            allGamesList.value!!.forEach{
//                Log.d("titi", it.title)
//            }
        }
    }

    suspend fun getRandomGameImage() {
        beerImageUrl.value = Uri.parse(gameRepository.getRandomGame().thumbnail.toString())
    }


}