package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.databinding.FragmentVersusBinding
import com.practice.gameapp.domain.models.VersusGame
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp
import java.time.LocalDate


class VersusFragment : Fragment() {
    private val versusViewModel: VersusViewModel   by activityViewModels()
    private val homeViewModel  : HomeViewModel     by activityViewModels()
    private val scoreViewModel : ScoreViewModel    by activityViewModels()
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!
    private var imageRandomOne = 0
    private var imageRandomTwo = 1
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentVersusBinding.inflate(inflater, container, false)


        versusViewModel.imageRandom.observe(viewLifecycleOwner, Observer {
            imageRandomOne = it!!
        })

        versusViewModel.imageRandom2.observe(viewLifecycleOwner, Observer {
            imageRandomTwo = it!!

        })

        versusViewModel.currenTime.observe(viewLifecycleOwner, Observer {
            binding.vsTimer.text = DateUtils.formatElapsedTime(it)
            binding.counteeer.text = counter.toString()
        })

        versusViewModel.counterScore.observe(viewLifecycleOwner, Observer {
            counter = it
            binding.counteeer.text = it.toString()
        })



        homeViewModel.allGamesList.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it[imageRandomOne].thumbnail)
                .fit()
                .centerInside()
                .into(binding.imageGame1)

            Picasso.get().load(it[imageRandomTwo].thumbnail)
                .fit()
                .centerInside()
                .into(binding.imageGame2)
        })

        binding.imageGame1.setOnClickListener {
            val dateStringGameOne =homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
            val dateStringGameTwo =homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate


            if (playGame(dateStringGameOne.toString(), dateStringGameTwo.toString())) {
                versusViewModel.setGameImageLose(imageRandomTwo)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomTwo)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame2)

            } else {
                versusViewModel.cancelTime()
                binding.dialogCompose.setContent {
                    DialogScore(score = counter, "You lose") {
                        gameOver(it, counter)
                    }
                }
            }
        }

        binding.imageGame2.setOnClickListener {
            val dateStringGameOne =homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
            val dateStringGameTwo =homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate

            if (playGame(dateStringGameTwo.toString(), dateStringGameOne.toString())) {

                versusViewModel.setGameImageLose(imageRandomOne)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomOne)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame1)
            } else {
                versusViewModel.cancelTime()
                binding.dialogCompose.setContent {
                    DialogScore(score = counter, "You lose") {
                        gameOver(it, counter)
                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {

                }
            }
        )

        return binding.root
    }

    private fun playGame(screenOne: String, screenTwo: String): Boolean {
        var assistant:Boolean = false
        val gameDateOne = LocalDate.parse(screenOne)
        val gameDateTwo = LocalDate.parse(screenTwo)
        if (gameDateOne <= gameDateTwo) {
            versusViewModel.setCounter()
            assistant = true
        }
        return assistant
    }

    private fun gameOver(name : String, score : Int){
        var scoreFinal = counter
        val scoreGame = ScoreEntity(0, name,scoreFinal,LocalDate.now().toString(), "vs")
        scoreViewModel.setScore(scoreGame)

        versusViewModel.resetCounter()

        Navigation
            .findNavController(requireView())
            .navigate(R.id.action_versusFragment_to_navigation_menugameversus)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}