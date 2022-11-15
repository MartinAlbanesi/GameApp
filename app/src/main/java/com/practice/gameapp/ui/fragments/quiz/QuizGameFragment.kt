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
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.databinding.FragmentQuizGameBinding
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.quiz.Question
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizGameFragment : Fragment() {
    //ViewModel
    private val quizGameViewModel: QuizViewModel by activityViewModels()

    private val gameEntity: GameEntity = GameEntity(2,"Overwatch 2","PC","Shooter","Mamasei mamasai mamakusa", "AA", "2022-25-13","Tu mama")

    //ViewBinding
    private var _binding: FragmentQuizGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*
    private val timerObserver = Observer<Long> { newTime ->
        binding.tvTimer.text = DateUtils.formatElapsedTime(newTime)
    }

    private val questionObserver = Observer<Question> { newQuestion ->
        Log.d("Entro al OBSERVER",newQuestion.getText(gameEntity))
        binding.tvQuestion.text = newQuestion.getText(gameEntity)
    }

     */

    private var flag = false

    private val endGameObserver = Observer<Boolean> {
        flag = it
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // val quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        _binding = FragmentQuizGameBinding.inflate(inflater, container, false)


        quizGameViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            Log.d("Entro al OBSERVER","cualquier cosa")
            binding.tvTimer.text = DateUtils.formatElapsedTime(it)
        })

        binding.cvEndgame.setContent {
            Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAA","WEEEEEEEEEEEEEEEEEEEEEEEE")
            quizGameViewModel.gameFinished.observe(viewLifecycleOwner,endGameObserver)
            if(flag){
                DialogScore(score = 1, state = "Quiz", onClick = {})
            }
        }
        /*
        quizGameViewModel.currenTime.observe(viewLifecycleOwner, timerObserver)
        quizGameViewModel.question.observe(viewLifecycleOwner,questionObserver)
        */
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}