package com.carlostorres.pruebakapitalcarlostoral.presentation.details

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity

data class DetailsState(
    val card: CardEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
