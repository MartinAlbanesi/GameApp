package com.practice.gameapp.data.repositories.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.gameapp.data.repositories.database.entities.ScoreEntity
import com.practice.gameapp.data.repositories.database.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    //private val scoreRepository: ScoreRepository
) : ViewModel() {



}