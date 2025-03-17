package com.carlostorres.pruebakapitalcarlostoral.data.remote.model

data class CardInfo(
    val id: Int,
    val card_images: List<CardImage>,
    val desc: String,
    val type: String,
    val humanReadableCardType: String,
    val archetype: String,
    val race: String,
    val name: String,
)