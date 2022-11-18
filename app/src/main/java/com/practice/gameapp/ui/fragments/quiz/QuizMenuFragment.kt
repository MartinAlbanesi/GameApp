package com.practice.gameapp.ui.fragments.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentQuizMenuBinding
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizMenuFragment : Fragment() {

    //ViewModel
    private val quizViewModel: QuizViewModel by activityViewModels()

    //ViewBinding
    private var _binding: FragmentQuizMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val difficulty: List<String> = listOf("Easy","Normal","Hard")
    private var time: Long = 20000 //20s
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizMenuBinding.inflate(inflater, container, false)

        binding.btnDifficulty.setOnClickListener {
            binding.btnDifficulty.text = difficulty[count]
            when(count) {
                0 -> time = 30000
                1 -> time = 20000
                2 -> time = 10000
            }
            if(count >= 2)
                count = 0
            else
                count ++
        }

        //Listeners
        binding.btnStart.setOnClickListener {
            quizViewModel.startTimer(time)
            quizViewModel.setQuestion()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_quizMenu_to_quizGameFragment)
        }

        binding.btnScoreboard.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.scoreFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}