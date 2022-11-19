package com.practice.gameapp.ui.viewmodels

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.gameapp.data.repositories.database.repository.GameDBRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VersusViewModel @Inject constructor(
) : ViewModel() {
    val CountDown_Timer = 60000L //Un minuto
    val ONE_SECOND = 1000L
    val DONE = 0L
    lateinit var timer: CountDownTimer
    val currenTime = MutableLiveData<Long>()
    var imageRandom = MutableLiveData<Int?>()
    var imageRandom2 = MutableLiveData<Int>()
    var gameFinished: Boolean = false
    val counter = MutableLiveData<Int>()


    fun setCounter(sum:Int){
        counter.value = sum
    }

    fun random(): Int {
        return (0..370).random()
    }

    fun setImage() {
        imageRandom.value = random()
        imageRandom2.value = random() //ver bien dsp
    }


    fun setGameImageLose(loserImage: Int) {
        if (imageRandom.value == loserImage) {
            imageRandom.value = random()
        }

        if (imageRandom2.value == loserImage) {
            imageRandom2.value = random()
        }
    }

        fun startTimer(){
            timer = object : CountDownTimer(CountDown_Timer, ONE_SECOND) {
                override fun onTick(millisUntilFinished: Long) {
                    currenTime.value = millisUntilFinished / ONE_SECOND
                }

                override fun onFinish() {
                    currenTime.value = DONE
                    gameFinished = true
                }
            }
            timer.start()
        }

     fun cancelTime() {
         timer.cancel()
     }
}