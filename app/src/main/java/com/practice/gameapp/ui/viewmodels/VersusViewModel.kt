package com.practice.gameapp.ui.viewmodels

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class VersusViewModel @Inject constructor(
) : ViewModel() {
    val CountDown_Timer = 60000L //Un minuto
    val ONE_SECOND = 1000L
    val DONE = 0L
    lateinit var timer: CountDownTimer
    val currentTime = MutableLiveData<Long>()
    var imageRandom = MutableLiveData<Int>()
    var imageRandom2 = MutableLiveData<Int>()
    var counterScore = MutableLiveData(0)
    var gameFinished = MutableLiveData<Boolean>(false)

    private fun random(): Int {
        return (0..370).random()
    }

    private fun setCounter() {
        counterScore.value = counterScore.value?.plus(1)
    }

    private fun resetCounter() {
        counterScore.value = 0
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

    fun gameOver(name : String, score : Int, onChange : (ScoreEntity) -> Unit ){
        val scoreGame = ScoreEntity(0, name,score, LocalDate.now().toString(), "vs")
        resetCounter()
        onChange(scoreGame)
    }

    fun playGame(screenOne: String, screenTwo: String): Boolean {
        var assistant:Boolean = false
        val gameDateOne = LocalDate.parse(screenOne)
        val gameDateTwo = LocalDate.parse(screenTwo)
        if (gameDateOne <= gameDateTwo) {
            setCounter()
            assistant = true
        }
        return assistant
    }

    fun startTimer() {
        timer = object : CountDownTimer(CountDown_Timer, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                currentTime.value = DONE
                gameFinished.value = true
            }
        }
        timer.start()
    }


    fun cancelTime() {
        timer.cancel()
    }
}