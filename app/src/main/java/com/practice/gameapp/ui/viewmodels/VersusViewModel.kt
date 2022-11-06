package com.practice.gameapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.data.repositories.database.repository.GameDBRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VersusViewModel @Inject constructor(
    private val gameDBRepository: GameDBRepositoryImpl
) : ViewModel() {


    var game: MutableLiveData<List<GameEntity>> = MutableLiveData<List<GameEntity>>()
    //gameDBRepository.allGames
    //MutableLiveData(listOf(GameEntity(0, "fill", "fill", "fill", "fill", "fill")))

    suspend fun fillName() {
        viewModelScope.launch(Dispatchers.IO) {
            val cualquierCosa = GameEntity(0, "fill", "fill", "fill", "fill", "fill")
            gameDBRepository.setGame(cualquierCosa)
//            game.value?.forEach{
//
//            //Log.d("titi", it.title)
//            }
        }
        //game.postValue(gameDBRepository.getAllGames("vs", 1).value)
    }
}