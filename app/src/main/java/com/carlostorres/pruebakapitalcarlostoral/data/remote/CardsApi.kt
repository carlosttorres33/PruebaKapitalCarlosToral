package com.carlostorres.pruebakapitalcarlostoral.data.remote

import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardsResponse
import com.carlostorres.pruebakapitalcarlostoral.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface CardsApi {

    @GET(Constants.ENDPOINT)
    suspend fun getCards() : Response<CardsResponse>

}