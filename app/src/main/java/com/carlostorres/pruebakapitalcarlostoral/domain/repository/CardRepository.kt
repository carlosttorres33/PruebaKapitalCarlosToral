package com.carlostorres.pruebakapitalcarlostoral.domain.repository

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCards(): List<CardEntity>

    suspend fun updateCard(cardEntity: CardEntity)

    fun getAllFavoriteCards(): Flow<List<CardEntity>>

}