package com.practice.gameapp.ui.fragments.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.databinding.FragmentQuizGameBinding
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate


@AndroidEntryPoint
class QuizGameFragment : Fragment() {

    //ViewModels
    private val quizGameViewModel: QuizViewModel by activityViewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()


    //ViewBinding
    private var _binding: FragmentQuizGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //Observers
    private val questionObserver = Observer<Question> { newQuestion ->
        binding.tvQuestion.text = newQuestion.getText(quizGameViewModel.selectedGame)
    }

    /*
    private val answersObserver = Observer<List<Question>> { newAnswer ->
        binding.btnOption1.text = newAnswer[0]
    }

     */

    //End game timer
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
        lifecycleScope.launch{
            quizGameViewModel.fillFourGames()
            quizGameViewModel.fillMutableFourGames()
            quizGameViewModel.fillSelectedGame()
        }

        _binding = FragmentQuizGameBinding.inflate(inflater, container, false)

        quizGameViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            binding.tvTimer.text = it.toString()
        })

        quizGameViewModel.gameFinished.observe(viewLifecycleOwner, endGameObserver)

        quizGameViewModel.selectedQuestion.observe(viewLifecycleOwner,questionObserver)

        //quizGameViewModel.mutableFourGames.observe(viewLifecycleOwner,answersObserver)

        /*
        quizGameViewModel.currenTime.observe(viewLifecycleOwner, timerObserver)
        */

        binding.btnOption1.text = quizGameViewModel.fourGameAnswers.getValue(quizGameViewModel.gamesIds[0])
        binding.btnOption2.text = quizGameViewModel.fourGameAnswers.getValue(quizGameViewModel.gamesIds[1])
        binding.btnOption3.text = quizGameViewModel.fourGameAnswers.getValue(quizGameViewModel.gamesIds[2])
        binding.btnOption4.text = quizGameViewModel.fourGameAnswers.getValue(quizGameViewModel.gamesIds[3])

        //When user clicks back button to exit the game
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {

                }
            }
        )

        binding.btnOption1.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[0].id)
        }

        binding.btnOption2.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[1].id)
        }

        binding.btnOption3.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[2].id)
        }

        binding.btnOption4.setOnClickListener {
            quizGameViewModel.game(quizGameViewModel.fourGames[3].id)
        }


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