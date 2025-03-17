package com.carlostorres.pruebakapitalcarlostoral.presentation.main

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo

data class MainState(
    val isLoading: Boolean = true,
    val cards: List<CardEntity> = emptyList(),
    val favoriteCards: List<CardEntity> = emptyList(),
    val error: String = ""
)
