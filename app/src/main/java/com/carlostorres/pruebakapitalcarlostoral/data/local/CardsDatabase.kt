package com.carlostorres.pruebakapitalcarlostoral.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity

@Database(entities = [CardEntity::class], version = 1, exportSchema = false)
abstract class CardsDatabase : RoomDatabase() {

    abstract fun getCardDao(): CardsDao

}