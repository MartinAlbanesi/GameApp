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
import com.practice.gameapp.domain.quiz.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.exitProcess

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
    var fourGames = mutableListOf<GameModel>()
    //var fourGameAnswers = MutableLiveData<List<GameModel>>()
    var selectedGame = GameModel("","","","","",0,"","")
    val selectedQuestion: MutableLiveData<Question> = MutableLiveData<Question>()
    private var gamesIds = mutableListOf<Int>()
    private var fourGameAnswers = mutableMapOf<Int,String>()
    var fourGameAnswers2 = MutableLiveData<MutableMap<Int,String>>()
    val gamesIds2 = MutableLiveData<List<Int>>()
    var score = MutableLiveData(0)


    //Fills a list with all the games from the API
    suspend fun fillGamesList() {
        viewModelScope.launch {
            gameRepository.getGames()
                .collect { gamesList ->
                    allQuizGamesList.addAll(gamesList)
                }
        }
    }

    //Fills a list with 4 random games from allQuizGamesList
    fun fillFourGames () {

        if(selectedQuestion.value == QuestionGenre()){
            var gameCount = 0
            lateinit var gameModel: GameModel

            val genreList = mutableListOf<String>()

            do {
                gameModel = allQuizGamesList.shuffled()[0]
                if(fourGames.isEmpty()){
                    fourGames.add(gameModel)
                    gamesIds.add(fourGames[gameCount].id)
                    gameCount++
                    genreList.add(gameModel.genre)
                }else{
                    if (!genreList.contains(gameModel.genre)){
                        fourGames.add(gameModel)
                        gamesIds.add(fourGames[gameCount].id)
                        gameCount++
                        genreList.add(gameModel.genre)
                    }
                }
            }while (gameCount != 4)
        }else if (selectedQuestion.value != QuestionGenre()){
            repeat(4){
                fourGames.add(allQuizGamesList.shuffled()[0])
                gamesIds.add(fourGames[it].id)
            }
        }

        gamesIds2.value = gamesIds
        fourGames.shuffled()


    }

    fun fillMutableFourGames() {
        when(selectedQuestion.value){
            is QuestionGenre -> {
                fourGames.forEach {
                    fourGameAnswers[it.id] = it.genre
                }
            }
            is QuestionTitleReleaseDate -> {
                fourGames.forEach {
                    fourGameAnswers[it.id] = it.title
                }
            }
            is QuestionDeveloper -> {
                fourGames.forEach {
                    fourGameAnswers[it.id] = it.developer
                }
            }
            is QuestionReleaseDate -> {
                fourGames.forEach {
                    fourGameAnswers[it.id] = it.releaseDate
                }
            }
        }
        Log.d("Wachipampa",fourGameAnswers2.value.toString())
        Log.d("Wachipampa2",fourGameAnswers.toString())
        fourGameAnswers2.value = fourGameAnswers
    }

    //Chooses a random GameModel from the list of 4 randoms
    fun fillSelectedGame() {
        selectedGame = fourGames.random()
    }

    fun setSelectedQuestion() {
        selectedQuestion.value = Questions.getRandomQuestion()
    }

    fun game(gameModelId: Int, gameOver: () -> Unit){
        if(gameModelId == selectedGame.id){
            setScore()
            fourGames.clear()
            gamesIds.clear()
            fourGameAnswers.clear()
            fillFourGames()
            Log.d("1",fourGames.toString())
            fillSelectedGame()
            Log.d("2",selectedGame.toString())
            setSelectedQuestion()
            Log.d("3",selectedQuestion.value.toString())
            fillMutableFourGames()
        }else{
            Log.d("respuesta","incorrecto")
            gameOver()
            gameFinished.value = true
            score.value = 0
        }
    }

    /**
     * Adds 1 point to score count
     */
    private fun setScore() {
        score.value = score.value?.plus(1)
    }

    /**
     * Controls timer during quiz game
     * @param couuntDown_TimerHard sets time based on difficulty
     */
    fun startTimer(couuntDown_TimerHard: Long) {
        timer = object : CountDownTimer(couuntDown_TimerHard, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                currenTime.value = millisUntilFinished / ONE_SECOND
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