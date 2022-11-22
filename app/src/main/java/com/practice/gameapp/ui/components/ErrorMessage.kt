package com.practice.gameapp.ui.components

import com.practice.gameapp.R

data class ErrorMessage(
    var active: Boolean,
    val message: String? = R.string.error_unknown.toString()
)