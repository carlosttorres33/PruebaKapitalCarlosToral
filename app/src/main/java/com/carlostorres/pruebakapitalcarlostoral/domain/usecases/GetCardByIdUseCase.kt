package com.carlostorres.pruebakapitalcarlostoral.domain.usecases

import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import javax.inject.Inject

class GetCardByIdUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(cardId: Int) = cardRepository.getCardById(cardId)

}