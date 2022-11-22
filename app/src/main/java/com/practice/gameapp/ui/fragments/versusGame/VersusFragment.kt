package com.practice.gameapp.ui.fragments.versusGame

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.practice.gameapp.R
import com.practice.gameapp.databinding.FragmentVersusBinding
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import com.squareup.picasso.Picasso


class VersusFragment : Fragment() {
    private val versusViewModel: VersusViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()
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

        versusViewModel.imageRandom.observe(viewLifecycleOwner) { imageViewModel ->
            imageRandomOne = imageViewModel!!
        }

        versusViewModel.imageRandom2.observe(viewLifecycleOwner) { imageViewModel->
            imageRandomTwo = imageViewModel!!
        }

        versusViewModel.currentTime.observe(viewLifecycleOwner) {
            binding.vsTimer.text = DateUtils.formatElapsedTime(it)

        }

        versusViewModel.counterScore.observe(viewLifecycleOwner) { ScoreViewM ->
            counter = ScoreViewM
            binding.counteeer.text = ScoreViewM.toString()
        }

        versusViewModel.gameFinished.observe(viewLifecycleOwner){ timeFinish ->
            if (timeFinish ) {
                versusViewModel.gameFinished.value = false
                gameOver("you lose")

            }
        }

        homeViewModel.allGamesList.observe(viewLifecycleOwner) {
            setImage(it[imageRandomOne].thumbnail,binding.imageGame1)
            setImage(it[imageRandomTwo].thumbnail,binding.imageGame2)
        }

        binding.imageGame1.setOnClickListener {
            if (versusViewModel.playGame(getImageReleaseDate(imageRandomOne), getImageReleaseDate(imageRandomTwo))) {
                versusViewModel.setGameImageLose(imageRandomTwo)
                setImage(getImageThumbnail(imageRandomTwo),binding.imageGame2)
            } else {
                versusViewModel.cancelTime()
                gameOver("You lose...")
            }
        }

        binding.imageGame2.setOnClickListener {
            if (versusViewModel.playGame(getImageReleaseDate(imageRandomOne), getImageReleaseDate(imageRandomTwo))) {
                versusViewModel.setGameImageLose(imageRandomOne)
                setImage(getImageThumbnail(imageRandomOne),binding.imageGame1)
            } else {
                versusViewModel.cancelTime()
                gameOver("You lose...")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //cancela volver atras durante la partida
                }
            }
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Setea una imagen con Picasso a una ImageView pasando una url
     * @param urlImage Url requerida para setear la imagen
     * @param imageView La ImagenView que deseas setear la imagen
     */
    private fun setImage(urlImage : String?, imageView: ImageView){
        Picasso.get().load(urlImage)
            .fit()
            .centerInside()
            .into(imageView)
    }

    /**
     * Funcion que se ejecuta cuando pierdes o el tiempo se termina
     * @param gameState El titulo que se pondra cuando pierdes o se termina el tiempo
     */
    private fun gameOver(gameState : String){
        binding.dialogCompose.setContent {
            DialogScore(score = counter, gameState) { nameUser ->
                versusViewModel.gameOver(nameUser, counter) { scoreEntity ->
                    scoreViewModel.setScore(scoreEntity)
                    Navigation
                        .findNavController(requireView())
                        .navigate(R.id.action_versusFragment_to_navigation_menugameversus)
                }
            }
        }
    }

    /**
     * Devuelve la fecha de la imagen solicitada
     * @param imageRandom Posicion de la lista de los juegos que deseas saber la fecha
     * @return String
     */
    private fun getImageReleaseDate(imageRandom : Int): String {
        return homeViewModel.allGamesList.value?.get(imageRandom)?.releaseDate.toString()
    }

    /**
     * Devuelve la url de la imagen solicitada
     * @param imageRandom Posicion de la lista de los juegos que deseas saber la fecha
     * @return String
     */
    private fun getImageThumbnail(imageRandom : Int): String {
        return homeViewModel.allGamesList.value?.get(imageRandom)?.thumbnail.toString()
    }
}