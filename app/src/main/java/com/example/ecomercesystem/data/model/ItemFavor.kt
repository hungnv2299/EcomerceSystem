package com.example.ecomercesystem.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite")
data class ItemFavor(
        @PrimaryKey
    val name:String,
    val imgsrc:String,
    val price:Double,
    val rating: Double
)

