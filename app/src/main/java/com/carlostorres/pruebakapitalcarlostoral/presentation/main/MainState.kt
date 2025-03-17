package com.carlostorres.pruebakapitalcarlostoral.presentation.main

import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo

data class MainState(
    val isLoading: Boolean = true,
    val cards: List<CardInfo> = emptyList(),
    val error: String = ""
)
