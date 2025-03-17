package com.carlostorres.pruebakapitalcarlostoral.data.local

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCardsDataSource @Inject constructor(
    private val cardsDao: CardsDao
) {

    suspend fun insertCard(cardEntity: CardEntity) {
        cardsDao.insertCard(cardEntity)
    }

    suspend fun updateCard(cardEntity: CardEntity) {
        cardsDao.updateCard(cardEntity)
    }

    fun getAllCards(): List<CardEntity> {
        return cardsDao.getAllCards()
    }

    fun getAllFavoriteCards(): Flow<List<CardEntity>> {
        return cardsDao.getAllFavoriteCards()
    }

    fun getActualCards(): List<CardEntity> {
        return cardsDao.getActualCards()
    }

}