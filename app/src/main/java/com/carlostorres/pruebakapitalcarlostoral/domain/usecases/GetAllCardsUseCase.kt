package com.carlostorres.pruebakapitalcarlostoral.domain.usecases

import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository

class GetAllCardsUseCase (
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(): List<CardInfo> {
        return cardRepository.getCards()
    }

}