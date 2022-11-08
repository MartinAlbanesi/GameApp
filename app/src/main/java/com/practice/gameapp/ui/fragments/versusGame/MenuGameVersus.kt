package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.GameEntity
import com.practice.gameapp.databinding.FragmentMenugameversusBinding
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import kotlinx.coroutines.launch


class MenuGameVersus : Fragment() {
    private var _binding: FragmentMenugameversusBinding? = null
    private val binding get() = _binding!!

    private val versusViewModel: VersusViewModel by activityViewModels()

    private val gameEntityObserver = Observer<List<GameEntity>> { newGame ->
        binding.textView.text = newGame.random().title
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentMenugameversusBinding.inflate(inflater,container,false)

        binding.buttonPlay.setOnClickListener {
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_menugameversus_to_versusFragment)
        }

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                versusViewModel.fillName()
            }
        }

        //versusViewModel.game.observe(viewLifecycleOwner,gameEntityObserver)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}