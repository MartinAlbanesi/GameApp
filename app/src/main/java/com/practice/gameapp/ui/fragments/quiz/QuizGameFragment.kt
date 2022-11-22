package com.practice.gameapp.ui.fragments.quiz

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.databinding.FragmentQuizGameBinding
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate


@AndroidEntryPoint
class QuizGameFragment : Fragment() {

    //ViewModels
    private val quizGameViewModel: QuizViewModel by viewModel()
    private val scoreViewModel: ScoreViewModel by activityViewModels()

    //ViewBinding
    private var _binding: FragmentQuizGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var idList: MutableList<Int> = mutableListOf()

    private var score: Int = 0

    //Observers
    private val questionObserver = Observer<Question> { newQuestion ->
        binding.tvQuestion.text = newQuestion.getText(quizGameViewModel.selectedGame)
    }

    //End game timer
    private val endGameObserver = Observer<Boolean> { endgameFlag ->
        if (endgameFlag) {
            binding.cvEndgame.setContent {
                DialogScore(score, "You lose") {
                    gameOver(it, score)
                }
            }
        }
    }

    private val gameIdsObserver = Observer<List<Int>> { ids ->
        idList.clear()
        for (item in ids) {
            idList.add(item)
        }
    }

    private val answersObserver = Observer<MutableMap<Int, String>> { answers ->
        binding.btnOption1.text = answers.getValue(idList[0])
        binding.btnOption2.text = answers.getValue(idList[1])
        binding.btnOption3.text = answers.getValue(idList[2])
        binding.btnOption4.text = answers.getValue(idList[3])
    }

    private val scoreObserver = Observer<Int> { actualScore ->
        binding.tvScore.text = actualScore.toString()
        score = actualScore
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        _binding = FragmentQuizGameBinding.inflate(inflater, container, false)

        quizGameViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            binding.tvTimer.text = it.toString()
        })

        quizGameViewModel.gameFinished.observe(viewLifecycleOwner, endGameObserver)

        quizGameViewModel.selectedQuestion.observe(viewLifecycleOwner, questionObserver)

        quizGameViewModel.gamesIds2.observe(viewLifecycleOwner, gameIdsObserver)

        quizGameViewModel.fourGameAnswers2.observe(viewLifecycleOwner, answersObserver)

        quizGameViewModel.score.observe(viewLifecycleOwner, scoreObserver)


        //When user clicks back button to exit the game
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Toast.makeText(context, R.string.exist_game, Toast.LENGTH_SHORT).show()
                }
            }
        )

        binding.btnOption1.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[0].id) {
                stateGame()
            }
        }

        binding.btnOption2.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[1].id) {
                stateGame()
            }
        }

        binding.btnOption3.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[2].id) {
                stateGame()
            }
        }

        binding.btnOption4.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[3].id) {
                stateGame()
            }
        }


        return binding.root
    }

    private fun stateGame() {
        quizGameViewModel.stopTimer()
        binding.cvEndgame.setContent {
            DialogScore(score, "") {
                gameOver(it, score)
                quizGameViewModel.gameFinished.value = false
            }
        }
    }

    private fun gameOver(name: String, score: Int) {

        val scoreGame = ScoreEntity(0, name, score, LocalDate.now().toString(), "quiz")

        scoreViewModel.setScore(scoreGame)

        quizGameViewModel.score.value = 0

        Navigation
            .findNavController(requireView())
            .navigate(R.id.navigation_quizMenu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}