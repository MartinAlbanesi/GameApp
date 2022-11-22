package com.practice.gameapp.ui.fragments.scores

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScoreFragment @Inject constructor(

) : Fragment() {

    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        composeView.setContent {
            val scoreGame by scoreViewModel.game.observeAsState()
            lateinit var score: ScoreEntity

            Column() {
                Scores(
                    if (scoreGame == "quiz") {
                        scoreViewModel.scoresQuiz
                    } else {
                        scoreViewModel.scoresVS
                    }
                ) {
                    score = it
                    scoreViewModel.deleteScore(score)
                }
            }
        }
    }


}