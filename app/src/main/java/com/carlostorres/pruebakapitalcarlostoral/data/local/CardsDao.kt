package com.carlostorres.pruebakapitalcarlostoral.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(cardEntity: CardEntity)

    @Update
    suspend fun updateCard(cardEntity: CardEntity)

    @Query("SELECT * FROM cards")
    fun getAllCards(): List<CardEntity>

    @Query("SELECT * FROM cards WHERE id = :cardId")
    fun getCardById(cardId: Int): CardEntity

    @Query("SELECT * FROM cards WHERE isFavorite = 1")
    fun getAllFavoriteCards(): Flow<List<CardEntity>>

    @Query("SELECT * FROM cards")
    fun getActualCards(): List<CardEntity>

}