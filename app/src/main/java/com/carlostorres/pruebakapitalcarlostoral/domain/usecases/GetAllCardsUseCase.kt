package com.carlostorres.pruebakapitalcarlostoral.domain.usecases

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class GetAllCardsUseCase (
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(): List<CardEntity> {
        return cardRepository.getCards()
    }

}