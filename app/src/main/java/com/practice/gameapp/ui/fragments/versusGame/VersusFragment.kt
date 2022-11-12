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
import com.practice.gameapp.ui.viewmodels.home.HomeViewModel
import com.practice.gameapp.ui.viewmodels.versusGame.VersusViewModel
import com.squareup.picasso.Picasso
import kotlin.random.Random

class VersusFragment: Fragment() {

    private val versusViewModel:VersusViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!
    private var imageRandomOne = 0
    private var imageRandomTwo= 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = FragmentVersusBinding.inflate(inflater,container,false)

        versusViewModel.imageRandom.observe(viewLifecycleOwner, Observer {
            imageRandomOne = it
        })

        versusViewModel.imageRandom2.observe(viewLifecycleOwner, Observer {
            imageRandomTwo= it
        })



        versusViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            binding.vsTimer.text = DateUtils.formatElapsedTime(it)
        })

        homeViewModel.allGamesList.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it[imageRandomOne].thumbnail).fit().centerInside()
                .into(binding.imageGame1)

            Picasso.get().load(it[imageRandomTwo].thumbnail).fit().centerInside()
                .into(binding.imageGame2)
        })


     //homeViewModel.allGamesList.observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}