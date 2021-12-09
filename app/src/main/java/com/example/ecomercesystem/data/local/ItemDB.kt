package com.example.ecomercesystem.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecomercesystem.data.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDB : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO

    companion object {
        @Volatile
        private var INSTANCE: ItemDB? = null

        fun getDB(context: Context): ItemDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDB::class.java,
                    "ItemDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}