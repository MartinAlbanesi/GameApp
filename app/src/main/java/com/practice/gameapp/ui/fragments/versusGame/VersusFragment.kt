package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.log
import kotlin.random.Random

class VersusFragment : Fragment() {

    private val versusViewModel: VersusViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!
    private var imageRandomOne = 0
    private var imageRandomTwo = 1

    // private var dateStringGameOne = homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
    //private var dateStringGameTwo = homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentVersusBinding.inflate(inflater, container, false)

        versusViewModel.imageRandom.observe(viewLifecycleOwner, Observer {
            imageRandomOne = it!!
        })

        versusViewModel.imageRandom2.observe(viewLifecycleOwner, Observer {
            imageRandomTwo = it
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

        binding.imageGame1.setOnClickListener {

            val dateStringGameOne =
                homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
            val dateStringGameTwo =
                homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate

            if (playGame(dateStringGameOne.toString(), dateStringGameTwo.toString())) {
                Log.d("d", "gano !")
                Log.d("d", dateStringGameOne.toString())
                Log.d("d", dateStringGameTwo.toString())
                versusViewModel.setGameImageLose(imageRandomTwo)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomTwo)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame2)
            } else {
                Log.d("d", "perdio !")
                Log.d("d", dateStringGameOne.toString())
                Log.d("d", dateStringGameTwo.toString())

                versusViewModel.cancelTime()

                Navigation.findNavController(requireView())
                    .navigate(R.id.action_versusFragment_to_navigation_menugameversus)

            }
        }
        binding.imageGame2.setOnClickListener {

            val dateStringGameOne =
                homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
            val dateStringGameTwo =
                homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate

            if (playGame(dateStringGameTwo.toString(), dateStringGameOne.toString())) {
                Log.d("d", "funciono !")
                Log.d("d", dateStringGameOne.toString())
                Log.d("d", dateStringGameTwo.toString())
                versusViewModel.setGameImageLose(imageRandomOne)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomOne)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame1)
            } else {
                Log.d("d", "NO funciono !")
                Log.d("d", dateStringGameOne.toString())
                Log.d("d", dateStringGameTwo.toString())

                versusViewModel.cancelTime()

                Navigation.findNavController(requireView())
                    .navigate(R.id.action_versusFragment_to_navigation_menugameversus)
            }
        }
        return binding.root
    }

    private fun playGame(uno: String, dos: String): Boolean {

        //   val dateStringGameOne = homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
        val gameDateOne = LocalDate.parse(uno)

        //   val dateStringGameTwo = homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate
        val gameDateTwo = LocalDate.parse(dos)

        if (gameDateOne <= gameDateTwo) {
            return true
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}