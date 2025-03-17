package com.carlostorres.pruebakapitalcarlostoral.data.remote

import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardsResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteCardsDataSource @Inject constructor(
    private val cardsApi: CardsApi
) {

    suspend fun getCards(): Response<CardsResponse> {
        return cardsApi.getCards()
    }

}