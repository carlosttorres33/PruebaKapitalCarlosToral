package com.carlostorres.pruebakapitalcarlostoral.data.repository

import android.util.Log
import com.carlostorres.pruebakapitalcarlostoral.data.local.LocalCardsDataSource
import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.data.remote.RemoteCardsDataSource
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import com.carlostorres.pruebakapitalcarlostoral.utils.InternetCheck
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImplementation @Inject constructor(
    private val remoteCardsDataSource: RemoteCardsDataSource,
    private val localCardsDataSource: LocalCardsDataSource
): CardRepository {

    override suspend fun getCards(): List<CardEntity> {

        return if (InternetCheck.isNetworkAvailable()) {
            getCardsFromApi()
        } else {
            localCardsDataSource.getAllCards()
        }

    }

    private suspend fun getCardsFromApi() : List<CardEntity>{

        val cardsListResponse = remoteCardsDataSource.getCards()
        val actualCards = localCardsDataSource.getActualCards()

        if (cardsListResponse.isSuccessful){
            cardsListResponse.body()?.data?.forEach { card ->

                Log.d("CardRepository", "Card: $card")

                if (actualCards.any { it.id == card.id }) return@forEach

                val newCard = CardEntity(
                    id = card.id,
                    cardUrl = card.card_images.first().image_url,
                    desc = card.desc,
                    type = card.type,
                    humanReadableCardType = card.humanReadableCardType,
                    archetype = card.archetype ?: "N/A",
                    name = card.name,
                    race = card.race,
                    isFavorite = false
                )
                localCardsDataSource.insertCard(newCard)
            }
        }

        return localCardsDataSource.getAllCards()

    }

    override suspend fun updateCard(cardEntity: CardEntity) {
        localCardsDataSource.updateCard(cardEntity)
    }

    override fun getAllFavoriteCards(): Flow<List<CardEntity>> {
        return localCardsDataSource.getAllFavoriteCards()
    }

}