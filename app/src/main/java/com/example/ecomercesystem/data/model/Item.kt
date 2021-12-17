package com.example.ecomercesystem.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    val name:String,
    val imgsrc:String,
    val category:String,
    val type:String,
    val price:Double,
    val rating: Double,
    val detail:String
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

@Entity(tableName = "cart")
data class ItemCart(
        @PrimaryKey
    val name:String,
    val imgsrc:String,
    val price:Double,
    val amount: Int
)

