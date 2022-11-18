package com.practice.gameapp.ui.fragments.quiz

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.databinding.FragmentQuizGameBinding
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class QuizGameFragment : Fragment() {
    //ViewModel
    private val quizGameViewModel: QuizViewModel by activityViewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private val gameEntity: GameEntity = GameEntity(
        2,
        "Overwatch 2",
        "PC",
        "Shooter",
        "Mamasei mamasai mamakusa",
        "AA",
        "2022-25-13",
        "Tu mama"
    )

    //ViewBinding
    private var _binding: FragmentQuizGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*
    private val timerObserver = Observer<Long> { newTime ->
        binding.tvTimer.text = DateUtils.formatElapsedTime(newTime)
    }
    */
    private val questionObserver = Observer<Question> { newQuestion ->
        Log.d("Entro al OBSERVER",newQuestion.getText(gameEntity))
        binding.tvQuestion.text = newQuestion.getText(gameEntity)
    }

    private val endGameObserver = Observer<Boolean> { endgameFlag ->
        if (endgameFlag) {
            binding.cvEndgame.setContent {
                DialogScore(score = 1, "You lose") {
                    gameOver(it, 1)
                }
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // val quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        _binding = FragmentQuizGameBinding.inflate(inflater, container, false)

        quizGameViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            Log.d("Entro al OBSERVER", "cualquier cosa")
            binding.tvTimer.text = it.toString()
        })
        quizGameViewModel.gameFinished.observe(viewLifecycleOwner, endGameObserver)

        /*
        quizGameViewModel.currenTime.observe(viewLifecycleOwner, timerObserver)
        */
        quizGameViewModel.question.observe(viewLifecycleOwner,questionObserver)


        return binding.root
    }

    private fun gameOver(name : String, score : Int){

        val scoreGame = ScoreEntity(0, name,score, LocalDate.now().toString(), "quiz")

        scoreViewModel.setScore(scoreGame)

        Navigation
            .findNavController(requireView())
            .navigate(R.id.navigation_quizMenu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}