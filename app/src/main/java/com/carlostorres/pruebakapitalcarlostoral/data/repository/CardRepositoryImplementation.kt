package com.carlostorres.pruebakapitalcarlostoral.data.repository

import com.carlostorres.pruebakapitalcarlostoral.data.remote.RemoteCardsDataSource
import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImplementation @Inject constructor(
    private val remoteCardsDataSource: RemoteCardsDataSource
): CardRepository {

    override suspend fun getCards(): List<CardInfo> {
        val response = remoteCardsDataSource.getCards()

        if (response.isSuccessful) {
            return response.body()?.data?.take(30) ?: emptyList()
        } else {
            throw Exception("Error al obtener las cards")
        }

    }

}