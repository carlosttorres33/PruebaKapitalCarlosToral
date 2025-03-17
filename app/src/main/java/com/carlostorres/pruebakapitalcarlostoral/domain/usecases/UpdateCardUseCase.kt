package com.carlostorres.pruebakapitalcarlostoral.domain.usecases

import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import javax.inject.Inject

class UpdateCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(cardEntity: CardEntity) = cardRepository.updateCard(cardEntity)

}