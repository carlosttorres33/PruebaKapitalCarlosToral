package com.carlostorres.pruebakapitalcarlostoral.di

import android.content.Context
import androidx.room.Room
import com.carlostorres.pruebakapitalcarlostoral.data.local.CardsDao
import com.carlostorres.pruebakapitalcarlostoral.data.local.CardsDatabase
import com.carlostorres.pruebakapitalcarlostoral.data.local.LocalCardsDataSource
import com.carlostorres.pruebakapitalcarlostoral.data.remote.CardsApi
import com.carlostorres.pruebakapitalcarlostoral.data.remote.RemoteCardsDataSource
import com.carlostorres.pruebakapitalcarlostoral.data.repository.CardRepositoryImplementation
import com.carlostorres.pruebakapitalcarlostoral.domain.repository.CardRepository
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.GetAllCardsUseCase
import com.carlostorres.pruebakapitalcarlostoral.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCardsApi(
        retrofit: Retrofit
    ) : CardsApi = retrofit.create(CardsApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteCardsDataSource(
        cardsApi: CardsApi
    ) : RemoteCardsDataSource = RemoteCardsDataSource(cardsApi)

    @Singleton
    @Provides
    fun provideCardsRepository(
        remoteCardsDataSource: RemoteCardsDataSource,
        localCardsDataSource: LocalCardsDataSource
    ) : CardRepository = CardRepositoryImplementation(
        remoteCardsDataSource = remoteCardsDataSource,
        localCardsDataSource = localCardsDataSource
    )

    @Singleton
    @Provides
    fun provideGetAllCardsUseCase(
        cardRepository: CardRepository
    ) : GetAllCardsUseCase = GetAllCardsUseCase(cardRepository)

    //region ROOM

    @Singleton
    @Provides
    fun provideCardsDatabase(
        @ApplicationContext app: Context
    ) : CardsDatabase = Room.databaseBuilder(
        app,
        CardsDatabase::class.java,
        "cards_db"
    ).build()

    @Singleton
    @Provides
    fun provideCardsDao(
        cardsDatabase: CardsDatabase
    ) = cardsDatabase.getCardDao()

    @Singleton
    @Provides
    fun provideLocalCardsDataSource(
        cardsDao: CardsDao
    ) : LocalCardsDataSource = LocalCardsDataSource(cardsDao)

    //endregion


}