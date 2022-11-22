package com.practice.gameapp.domain.models

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    val status: Int,
    @SerializedName("status_message") val statusMessage: String
)