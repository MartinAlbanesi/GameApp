package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.databinding.FragmentVersusBinding
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.home.HomeViewModel
import com.practice.gameapp.ui.viewmodels.score.ScoreViewModel
import com.practice.gameapp.ui.viewmodels.versusGame.VersusViewModel
import com.squareup.picasso.Picasso
import java.time.LocalDate

class VersusFragment : Fragment() {

    private val versusViewModel: VersusViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val scoreViewModel : ScoreViewModel by activityViewModels()
    private var _binding: FragmentVersusBinding? = null
    private val binding get() = _binding!!
    private var imageRandomOne = 0
    private var imageRandomTwo = 1

    private lateinit var composeView: ComposeView

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
                versusViewModel.setGameImageLose(imageRandomTwo)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomTwo)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame2)
            } else {

                versusViewModel.cancelTime()

                binding.dialogCompose.setContent {

                    DialogScore(score = 1, "You lose") {
                        gameOver(it, 1)
                    }
                }
            }
        }
        binding.imageGame2.setOnClickListener {

            val dateStringGameOne =
                homeViewModel.allGamesList.value?.get(imageRandomOne)?.releaseDate
            val dateStringGameTwo =
                homeViewModel.allGamesList.value?.get(imageRandomTwo)?.releaseDate

            if (playGame(dateStringGameTwo.toString(), dateStringGameOne.toString())) {

                versusViewModel.setGameImageLose(imageRandomOne)
                Picasso.get().load(homeViewModel.allGamesList.value?.get(imageRandomOne)?.thumbnail)
                    .fit().centerInside()
                    .into(binding.imageGame1)
            } else {

                versusViewModel.cancelTime()

                binding.dialogCompose.setContent {

                    DialogScore(score = 1, "You lose") {
                        gameOver(it, 1)
                    }
                }
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

    private fun gameOver(name : String, score : Int){

        val scoreGame = ScoreEntity(0, name,score,LocalDate.now().toString(), "vs")

        scoreViewModel.setScore(scoreGame)

        Navigation
            .findNavController(requireView())
            .navigate(R.id.action_versusFragment_to_navigation_menugameversus)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}