package com.practice.gameapp.ui.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.domain.quiz.QuestionGenre
import com.practice.gameapp.domain.quiz.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {
    //val couuntDown_TimerHard = 10000L //10 seg
    //Timer
    val ONE_SECOND = 1000L
    val DONE = 0L
    val currenTime = MutableLiveData<Long>()
    private lateinit var timer: CountDownTimer
    var gameFinished: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    //Games
    private var allQuizGamesList = mutableListOf(GameModel("fill", "fill", "fill", "fill", "fill", 0, "",""))
    private var fourGames = mutableListOf<GameModel>()
    var mutableFourGames = MutableLiveData<MutableList<GameModel>>()
    var selectedGame = GameModel("","","","","",0,"","")
    //Questions and answers
    val selectedQuestion: MutableLiveData<Question> = MutableLiveData<Question>()
    val allFourQuestions: MutableLiveData<MutableList<Question>> = MutableLiveData<MutableList<Question>>()
    val otherThreeQuestions: MutableLiveData<Question> = MutableLiveData<Question>()

    //Fills a list with all the games from the API
    suspend fun fillGamesList() {
        viewModelScope.launch {
            gameRepository.getGames()
                .collect { gamesList ->
                    allQuizGamesList.addAll(gamesList)
                    allQuizGamesList.forEach{
                        Log.d("AAAAAAAAAAAAAA",it.title)
                    }
                }
        }
    }

    //Fills a list with 4 random games from allQuizGamesList
    fun fillFourGames () {
        repeat(4){
            fourGames.add(allQuizGamesList.shuffled()[0])
            Log.d("Cuatro juegos",fourGames[it].toString())
        }
    }

    fun fillMutableFourGames() {
        mutableFourGames.value?.addAll(fourGames)
        mutableFourGames.value?.shuffle()
    }

    //Chooses a random GameModel from the list of 4 randoms
    fun fillSelectedGame() {
        selectedGame = fourGames.random()
    }

    fun setSelectedQuestion() {
        selectedQuestion.value = Questions.getRandomQuestion()
    }

    /*
    fun setOtherThreeQuestions() {
        selectedQuestion.value
    }
     */

    //Timer
    fun startTimer(couuntDown_TimerHard: Long) {
        timer = object : CountDownTimer(couuntDown_TimerHard, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                currenTime.value = millisUntilFinished / ONE_SECOND
                //Log.d("test", currenTime.value.toString())
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