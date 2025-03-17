package com.carlostorres.pruebakapitalcarlostoral.domain.repository

import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo

interface CardRepository {

    suspend fun getCards(): List<CardInfo>

}