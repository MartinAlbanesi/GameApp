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
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizMenuFragment : Fragment() {

    //ViewModel
    private val quizViewModel: QuizViewModel by activityViewModels()
    private val scoreViewModel : ScoreViewModel by activityViewModels()

    //ViewBinding
    private var _binding: FragmentQuizMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val quizViewModel =
            ViewModelProvider(this)[QuizViewModel::class.java]

        _binding = FragmentQuizMenuBinding.inflate(inflater, container, false)

        //Listeners
        binding.btnStart.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_quizMenu_to_quizGameFragment)
        }

        binding.btnScoreboard.setOnClickListener {
            scoreViewModel.searchGame("quiz")
            Navigation.findNavController(requireView()).navigate(R.id.scoreFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}