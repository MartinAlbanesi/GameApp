package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentHomeBinding
import com.practice.gameapp.databinding.FragmentVersusBinding

class VersusFragment: Fragment() {
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVersusBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}