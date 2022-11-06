package com.practice.gameapp.ui.fragments.versusGame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentHomeBinding
import com.practice.gameapp.databinding.FragmentMenugameversusBinding


class MenuGameVersus : Fragment() {
    private var _binding: FragmentMenugameversusBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentMenugameversusBinding.inflate(inflater,container,false)

        binding.buttonPlay.setOnClickListener {
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_menugameversus_to_versusFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}