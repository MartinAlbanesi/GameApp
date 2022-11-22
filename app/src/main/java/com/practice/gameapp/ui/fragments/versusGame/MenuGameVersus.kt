package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentMenugameversusBinding
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class MenuGameVersus : Fragment() {
    private var _binding: FragmentMenugameversusBinding? = null
    private val binding get() = _binding!!
    private val versusViewModel: VersusViewModel by viewModel()
    private val scoreViewModel: ScoreViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenugameversusBinding.inflate(inflater, container, false)

        binding.buttonPlay.setOnClickListener {
            versusViewModel.setImage()
            versusViewModel.startTimer()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_menugameversus_to_versusFragment)
        }

        binding.buttonScore.setOnClickListener {

            scoreViewModel.searchGame("vs")

            Navigation.findNavController(requireView())
                .navigate(R.id.scoreFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}