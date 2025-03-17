package com.carlostorres.pruebakapitalcarlostoral.data.local.model

import androidx.room.Entity

@Entity(tableName = "cards")
data class CardEntity(
    val id: Int,
    val cardUrl: String,
    val desc: String,
    val type: String,
    val humanReadableCardType: String,
    val archetype: String,
    val race: String,
    val name: String
)
