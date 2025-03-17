package com.carlostorres.pruebakapitalcarlostoral.domain.usecases

import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import javax.inject.Inject

class GetFavoriteCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke() = cardRepository.getAllFavoriteCards()
}