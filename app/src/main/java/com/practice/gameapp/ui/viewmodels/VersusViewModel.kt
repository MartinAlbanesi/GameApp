package com.practice.gameapp.ui.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
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

    val CountDown_Timer = 60000L //Un minuto
    val ONE_SECOND = 1000L
    val DONE = 0L
    lateinit var  timer:CountDownTimer
    val currenTime = MutableLiveData<Long>()


    init {
        timer = object:CountDownTimer(CountDown_Timer,ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                currenTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                currenTime.value = DONE
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }









/*
    suspend fun fillName() {
        viewModelScope.launch(Dispatchers.IO) {
            val cualquierCosa = GameEntity(0, "fill", "fill", "fill", "fill", "fill")
            gameDBRepository.setGame(cualquierCosa)
//            game.value?.forEach{
//
//            Log.d("titi", it.id.toString())
//            }
        }
        //game.postValue(gameDBRepository.getAllGames("vs", 1).value)
    }*/
}