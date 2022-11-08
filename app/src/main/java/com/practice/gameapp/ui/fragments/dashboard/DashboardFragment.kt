package com.practice.gameapp.ui.fragments.dashboard

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.ui.fragments.scores.DialogScore
import com.practice.gameapp.ui.fragments.scores.Scores
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment @Inject constructor(

) : Fragment() {

    private val scoreViewModel: ScoreViewModel by activityViewModels()

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        composeView.setContent {
            Column() {
                Scores()
                DialogScore(1, { algo() })
            }
        }
    }

    private fun algo() {
        Toast.makeText(
            requireContext(),
            "asdasdsadsadsada",
            Toast.LENGTH_SHORT)
            .show()
        val date = LocalDate.now()
        scoreViewModel.setScore(ScoreEntity(0,"a",2,date.toString(),"vs"))
    }
}

