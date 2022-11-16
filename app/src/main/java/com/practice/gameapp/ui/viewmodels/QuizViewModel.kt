package com.practice.gameapp.ui.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.domain.quiz.QuestionGenre
import com.practice.gameapp.domain.quiz.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    val couuntDown_TimerHard = 10000L //10 seg
    val ONE_SECOND = 1000L
    val DONE = 0L
    val currenTime = MutableLiveData<Long>()
    lateinit var timer: CountDownTimer
    var gameFinished: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val question: MutableLiveData<Question> = MutableLiveData<Question>()

    fun setQuestion() {
        question.value = Questions.getRandomQuestion()
    }


    fun startTimer() {
        timer = object : CountDownTimer(couuntDown_TimerHard, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                currenTime.value = millisUntilFinished / ONE_SECOND
                Log.d("test", currenTime.value.toString())
            }

            override fun onFinish() {
                currenTime.value = DONE
                gameFinished.value = true
            }
        }
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}