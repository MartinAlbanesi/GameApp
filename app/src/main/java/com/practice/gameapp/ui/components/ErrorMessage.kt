package com.practice.gameapp.ui.components

data class ErrorMessage(
    var active: Boolean,
    val message: String? = "Unknown Error"
)