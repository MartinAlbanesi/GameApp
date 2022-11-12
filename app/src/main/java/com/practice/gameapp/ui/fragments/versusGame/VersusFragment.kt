package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentHomeBinding
import com.practice.gameapp.databinding.FragmentVersusBinding
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel

class VersusFragment: Fragment() {

    private val versusViewModel:VersusViewModel by activityViewModels()
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVersusBinding.inflate(inflater,container,false)

        versusViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            binding.vsTimer.text = DateUtils.formatElapsedTime(it)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}